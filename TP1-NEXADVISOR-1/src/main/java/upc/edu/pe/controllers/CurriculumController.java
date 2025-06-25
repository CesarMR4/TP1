package upc.edu.pe.controllers;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import upc.edu.pe.entities.Curriculum;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.serviceimplements.AnalizadorTextoServiceImpl;
import upc.edu.pe.serviceinterface.CurriculumService;
import upc.edu.pe.serviceinterface.ReservaService;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private AnalizadorTextoServiceImpl analizadorTextoService;

    @PostMapping(value = "/analizar/{idReserva}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirYAnalizarCurriculum(
            @PathVariable int idReserva,
            @RequestParam("archivo") MultipartFile archivo) {

        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Reserva no encontrada\"}");
        }

        Reserva reserva = reservaOpt.get();

        String textoExtraido = "";
        try {
            textoExtraido = analizadorTextoService.extraerTexto(archivo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\": \"No se pudo extraer el texto\"}");
        }

        String reporteIA = analizadorTextoService.analizarTexto(textoExtraido);

        try {
            Curriculum curriculumAnalizado = new Curriculum();
            curriculumAnalizado.setTextoCurriculum(textoExtraido);
            curriculumAnalizado.setReporteIA(reporteIA);
            curriculumAnalizado.setReserva(reserva);

            curriculumService.guardarCurriculumAnalizado(curriculumAnalizado);

            return ResponseEntity.ok(reporteIA);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\": \"No se pudo guardar el archivo\"}");
        }
    }

    @GetMapping("/reporte/{idReserva}")
    public Curriculum obtenerReportePorReserva(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return null;
        }
        Optional<Curriculum> curriculumOpt = curriculumService.buscarPorReserva(reservaOpt.get());
        return curriculumOpt.orElse(null);
    }

    @GetMapping(value = "/reporte/pdf/{idReserva}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarReportePdf(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Curriculum> curriculumOpt = curriculumService.buscarPorReserva(reservaOpt.get());
        if (!curriculumOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        String reporte = curriculumOpt.get().getReporteIA();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Reporte de análisis del currículum"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(reporte));

            document.close();

            byte[] pdfBytes = baos.toByteArray();

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition", "attachment; filename=reporte_curriculum.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/reporte/{idReserva}/pdf")
    public ResponseEntity<byte[]> descargarReportePDF(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Curriculum> curriculumOpt = curriculumService.buscarPorReserva(reservaOpt.get());
        if (!curriculumOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Curriculum curriculum = curriculumOpt.get();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("🔎 Reporte generado por IA sobre el currículum:\n"));

            // Procesar JSON legible
            ObjectMapper mapper = new ObjectMapper();
            ReporteIA reporteIA = mapper.readValue(curriculum.getReporteIA(), ReporteIA.class);

            if (reporteIA.matches != null && !reporteIA.matches.isEmpty()) {
                for (Match m : reporteIA.matches) {
                    document.add(new Paragraph("🔸 Error: " + m.message));
                    document.add(new Paragraph("   📌 Contexto: " + m.context.text.trim()));
                    if (m.replacements != null && !m.replacements.isEmpty()) {
                        String sugerencias = m.replacements.stream()
                                .map(r -> r.value)
                                .limit(5)
                                .reduce((a, b) -> a + ", " + b).orElse("");
                        document.add(new Paragraph("   💡 Sugerencias: " + sugerencias));
                    }
                    document.add(new Paragraph(" "));
                }
            } else {
                document.add(new Paragraph("✅ No se encontraron errores ortográficos."));
            }

            document.close();

            byte[] pdfBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "reporte_curriculum.pdf");

            return ResponseEntity.ok().headers(headers).body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // ✅ Clases internas con protección para ignorar campos no esperados del JSON
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReporteIA {
        public List<Match> matches;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Match {
        public String message;
        public Context context;
        public List<Replacement> replacements;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Context {
        public String text;
        public int offset;
        public int length;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Replacement {
        public String value;
    }
}
