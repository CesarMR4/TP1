package upc.edu.pe.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping(value = "/analizar/{idReserva}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String subirYAnalizarCurriculum(@PathVariable int idReserva, @RequestBody String textoCurriculum) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);  


        if (!reservaOpt.isPresent()) {
            return "{\"error\": \"Reserva no encontrada\"}";
        }

        Reserva reserva = reservaOpt.get();

        String reporteIA = analizadorTextoService.analizarTexto(textoCurriculum);

        Curriculum curriculumAnalizado = new Curriculum(textoCurriculum, reporteIA, reserva);
        curriculumService.guardarCurriculumAnalizado(curriculumAnalizado);

        return reporteIA;
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
