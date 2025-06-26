package upc.edu.pe.controllers;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            curriculumAnalizado.setTextoCurriculum(archivo.getBytes());
           

         // 👉 Agrega esto para depurar el tipo de dato que se está intentando guardar
         System.out.println("Clase del contenido de textoCurriculum: " + curriculumAnalizado.getTextoCurriculum().getClass().getName());
         System.out.println("Bytes del archivo: " + curriculumAnalizado.getTextoCurriculum().length);

            
            // 👉 Agrega esta línea para depurar:
            System.out.println("Bytes del archivo: " + curriculumAnalizado.getTextoCurriculum().length);

            curriculumAnalizado.setReporteIA(reporteIA);
            curriculumAnalizado.setReserva(reserva);

            curriculumService.guardarCurriculumAnalizado(curriculumAnalizado);

            return ResponseEntity.ok(reporteIA);
        } catch (Exception e) {
            e.printStackTrace(); // 👉 También útil para ver el stacktrace completo
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
            // Crear PDF con el reporteIA
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Reporte generado por IA sobre el currículum:\n\n"));
            document.add(new Paragraph(curriculum.getReporteIA()));
            document.close();

            byte[] pdfBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "reporte_curriculum.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
