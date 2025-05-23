package upc.edu.pe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import upc.edu.pe.entities.Reserva;
import upc.edu.pe.serviceinterface.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private NotificacionController notificacionController;


    @PostMapping
    public void registrarReserva(@RequestBody Reserva reserva) {
        reservaService.insert(reserva);
        // Suponiendo que en el objeto reserva ya viene el asesor elegido
        int idAsesor = reserva.getAsesor().getId_Asesor();;   // ajusta esto según tu modelo

        String mensaje = "Nuevo estudiante ha realizado una reserva.";
        notificacionController.notificarReserva(idAsesor, mensaje);
        
    }

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.list();
    }


    @DeleteMapping
    public void eliminarReserva(@RequestParam int id) {
        reservaService.delete(id);
    }


    @PostMapping("/actualizarEstado")
    public void actualizarEstado(@RequestParam int idReserva, @RequestParam String estado) {
        reservaService.updateEstado(idReserva, estado);
    }

    @PutMapping("/comentario/{id}")
    public void actualizarComentario(@PathVariable("id") Integer idReserva, @RequestBody String comentario) {
        reservaService.actualizarComentario(idReserva, comentario);
    }
}
