package upc.edu.pe.serviceimplements;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.repositories.PuntuacionRepository;
import upc.edu.pe.serviceinterface.PuntuacionService;

@Service
public class PuntuacionServiceImpl implements PuntuacionService {

    @Autowired
    private PuntuacionRepository pRepo;

	@Override
	public void insertar(Puntuacion puntuacion) {
		// TODO Auto-generated method stub
		  pRepo.save(puntuacion);
	}

	@Override
	public Optional<Puntuacion> obtenerPorReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		return pRepo.findByReserva(reserva);
	}

	@Override
	public Double obtenerPromedioPorAsesor(int idAsesor) {
		// TODO Auto-generated method stub
		  return pRepo.obtenerPromedioPorAsesor(idAsesor);
	}
    

}
