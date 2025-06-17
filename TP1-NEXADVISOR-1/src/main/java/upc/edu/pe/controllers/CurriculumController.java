package upc.edu.pe.controllers;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

        String textoExtraido = ""; // Esto debe ser el resultado de extraer texto del archivo PDF/DOCX
        try {
            // Aquí deberías tener un método que extrae el texto del archivo
            textoExtraido = analizadorTextoService.extraerTexto(archivo); // Ejemplo
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\": \"No se pudo extraer el texto\"}");
        }

        String reporteIA = analizadorTextoService.analizarTexto(textoExtraido);

        try {
            Curriculum curriculumAnalizado = new Curriculum();
            curriculumAnalizado.setTextoCurriculum(archivo.getBytes()); // Guarda el archivo
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
}
