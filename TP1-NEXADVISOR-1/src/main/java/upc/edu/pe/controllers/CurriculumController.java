package upc.edu.pe.controllers;

import java.io.InputStream;
import java.util.Optional;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import upc.edu.pe.entities.Curriculum;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.serviceinterface.CurriculumService;
import upc.edu.pe.serviceinterface.ReservaService;


@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @Autowired
    private ReservaService reservaService;

    @PostMapping(value = "/analizar/{idReserva}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> analizarCurriculum(@PathVariable int idReserva,
                                                     @RequestParam("archivo") MultipartFile archivo) {
        try {
            Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
            if (!reservaOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Reserva no encontrada");
            }

            Reserva reserva = reservaOpt.get();

            InputStream inputStream = archivo.getInputStream();
            String textoExtraido = new Tika().parseToString(inputStream);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", textoExtraido);
            params.add("language", "es");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            RestTemplate restTemplate = new RestTemplate();
            String respuestaIA = restTemplate.postForObject("https://api.languagetool.org/v2/check", request, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(respuestaIA);

            StringBuilder errores = new StringBuilder();
            errores.append("Errores detectados:\n\n");

            for (JsonNode match : root.get("matches")) {
                String mensaje = match.get("message").asText();
                String tipo = match.get("rule").get("issueType").asText();
                String categoria = match.get("rule").get("category").get("name").asText();
                String contexto = match.get("context").get("text").asText();

                errores.append("⚠️ Tipo: ").append(tipo)
                       .append(" | Categoría: ").append(categoria)
                       .append("\n→ ").append(mensaje)
                       .append("\nTexto: ").append(contexto)
                       .append("\n\n");
            }

            String reporteFinal = "Texto Original:\n" + textoExtraido +
                                  "\n\n--- Análisis IA ---\n" + errores;

            Curriculum curriculum = new Curriculum(reporteFinal, reserva);
            curriculumService.guardarCurriculum(curriculum);

            return ResponseEntity.ok(reporteFinal);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    @GetMapping("/reporte/{idReserva}")
    public ResponseEntity<String> obtenerReportePorReserva(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Reserva no encontrada");
        }

        Optional<Curriculum> curriculumOpt = curriculumService.buscarPorReserva(reservaOpt.get());
        if (curriculumOpt.isPresent()) {
            return ResponseEntity.ok(curriculumOpt.get().getReporteIA());
        } else {
            return ResponseEntity.badRequest().body("No hay currículum asociado a esta reserva");
        }
    }
    
    @GetMapping("/reporte/{idReserva}/pdf")
    /*
    public ResponseEntity<byte[]> descargarPDF(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Curriculum> curriculumOpt = curriculumService.buscarPorReserva(reservaOpt.get());
        if (!curriculumOpt.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String contenido = curriculumOpt.get().getReporteIA();

        try {
        
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, out);
            document.open();
            document.add(new com.itextpdf.text.Paragraph(contenido));
            document.close();

            byte[] pdfBytes = out.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition
                    .attachment()
                    .filename("cv_reserva_" + idReserva + ".pdf")
                    .build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
*/
    public ResponseEntity<byte[]> descargarPDF(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Curriculum> curriculumOpt = curriculumService.buscarPorReserva(reservaOpt.get());
        if (!curriculumOpt.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String contenido = curriculumOpt.get().getReporteIA();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, out);

         
            writer.setCompressionLevel(9);

            document.open();

      
            String[] lineas = contenido.split("\\r?\\n");
            for (String linea : lineas) {
                if (linea == null || linea.trim().isEmpty()) {
                
                    document.add(new com.itextpdf.text.Paragraph(" "));
                    continue;
                }

            
                final int MAX_BLOCK = 1000; 
                if (linea.length() <= MAX_BLOCK) {
                    document.add(new com.itextpdf.text.Paragraph(linea));
                } else {
                    int start = 0;
                    while (start < linea.length()) {
                        int end = Math.min(start + MAX_BLOCK, linea.length());
                        String bloque = linea.substring(start, end);
                        document.add(new com.itextpdf.text.Paragraph(bloque));
                        start = end;
                    }
                }
            }

            document.close();

            byte[] pdfBytes = out.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition
                    .attachment()
                    .filename("cv_reserva_" + idReserva + ".pdf")
                    .build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    
    
}
