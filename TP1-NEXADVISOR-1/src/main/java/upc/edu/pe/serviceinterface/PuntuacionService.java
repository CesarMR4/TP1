package upc.edu.pe.serviceinterface;


import java.util.Optional;

import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.entities.Reserva;

public interface PuntuacionService {
    
	   void insertar(Puntuacion puntuacion);
	    Optional<Puntuacion> obtenerPorReserva(Reserva reserva);
	    Double obtenerPromedioPorAsesor(int idAsesor);
}

