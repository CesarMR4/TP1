package upc.edu.pe.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.controllers.NotificacionController;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.repositories.ReservaRepository;
import upc.edu.pe.serviceinterface.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService{
	
    @Autowired 
	private ReservaRepository reservaRepository;
    @Autowired
    private NotificacionController notificacionController;

	@Override
	public void insert(Reserva reserva) {
		// TODO Auto-generated method stub
		 reservaRepository.save(reserva);
		 int idAsesor = reserva.getAsesor().getId(); 
		 String mensaje = "Se ha registrado una nueva reserva.";
		 notificacionController.notificarReserva(idAsesor, mensaje);
	}

	@Override
	public List<Reserva> list() {
		// TODO Auto-generated method stub
		return reservaRepository.findAll();
	}

	@Override
	public void delete(int id) {
		 reservaRepository.deleteById(id); 
		
	}

	@Override
	public void updateEstado(int idReserva, String estado) {
		Optional<Reserva> reserva = reservaRepository.findById(idReserva);  
        if (reserva.isPresent()) {
            reserva.get().setEstado(estado);  
            reservaRepository.save(reserva.get()); 
        }

	}

	@Override
	public void actualizarComentario(int idReserva, String comentarioAsesor) {
	    Optional<Reserva> reserva = reservaRepository.findById(idReserva);
	    if (reserva.isPresent()) {
	        reserva.get().setComentarioAsesor(comentarioAsesor);
	        reservaRepository.save(reserva.get());	
	    }
	}


	@Override
	public List<Reserva> listarPorEstudiante(int id) {
		return reservaRepository.findByEstudiante_Id(id);

	}
	@Override
	public List<Reserva> listarPorAsesor(int idAsesor) {
	    return reservaRepository.findByAsesorId(idAsesor);
	}

	@Override
	public Optional<Reserva> buscarPorId(int id) {
		// TODO Auto-generated method stub
		 return reservaRepository.findById(id);
	}

}
