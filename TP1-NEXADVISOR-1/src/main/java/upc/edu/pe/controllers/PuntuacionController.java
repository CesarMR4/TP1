package upc.edu.pe.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.serviceinterface.PuntuacionService;
import upc.edu.pe.serviceinterface.ReservaService;

@RestController
@RequestMapping("/puntuaciones")
public class PuntuacionController {

    @Autowired
    private PuntuacionService pService;

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/registrar/{idReserva}")
    public ResponseEntity<Map<String, String>> registrar(@PathVariable int idReserva, @RequestBody Puntuacion puntuacion) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Reserva no encontrada"));
        }

        Reserva reserva = reservaOpt.get();

        if (pService.obtenerPorReserva(reserva).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Ya se ha registrado una puntuación para esta reserva."));
        }

        puntuacion.setReserva(reserva);
        pService.insertar(puntuacion);
        return ResponseEntity.ok(Map.of("mensaje", "Puntuación registrada correctamente."));
    }


    @GetMapping("/promedio/{idAsesor}")
    public ResponseEntity<Double> promedioPorAsesor(@PathVariable int idAsesor) {
        Double promedio = pService.obtenerPromedioPorAsesor(idAsesor);
        return ResponseEntity.ok(promedio != null ? promedio : 0.0);
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<Puntuacion> obtenerPorReserva(@PathVariable int idReserva) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorId(idReserva);
        if (!reservaOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Puntuacion> puntuacionOpt = pService.obtenerPorReserva(reservaOpt.get());
        return puntuacionOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}