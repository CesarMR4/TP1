package upc.edu.pe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import upc.edu.pe.dto.Notificacion;

@Controller
public class NotificacionController {
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	public void notificarReserva(int idAsesor, String mensaje) {
	    Notificacion notificacion = new Notificacion(mensaje);
	    messagingTemplate.convertAndSend("/notificacion/reservas/" + idAsesor, notificacion);
	}

}
