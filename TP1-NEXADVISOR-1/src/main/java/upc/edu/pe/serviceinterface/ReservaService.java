package upc.edu.pe.serviceinterface;

import java.util.List;

import upc.edu.pe.entities.Reserva;

public interface ReservaService {
	
    public void insert(Reserva reserva);
    public List<Reserva> list();
    public void delete(int id);
    public void updateEstado(int idReserva, String estado);
   // public List<Reserva> listByFecha(String fecha);
}
