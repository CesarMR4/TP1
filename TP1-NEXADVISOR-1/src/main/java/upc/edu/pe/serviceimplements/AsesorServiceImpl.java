package upc.edu.pe.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Asesor;
import upc.edu.pe.repositories.AsesorRepository;
import upc.edu.pe.serviceinterface.AsesorService;

@Service
public class AsesorServiceImpl implements AsesorService {

	@Autowired
	private AsesorRepository asesorRespository;
	@Override
	public void insert(Asesor asesor) {
		// TODO Auto-generated method stub
		asesorRespository.save(asesor);
	}

	@Override
	public List<Asesor> list() {
		return asesorRespository.findAll();
	}

	@Override
	public void delete(int id) {
		asesorRespository.deleteById(id);
	}

	@Override
	public Optional<Asesor> listId(int id) {
		return asesorRespository.findById(id);
	}

	@Override
	public void update(Asesor asesor) {
		 asesorRespository.save(asesor);

	}
	@Override
	public Optional<Asesor> login(String email, String password) {
		return asesorRespository.findByEmailAndPassword(email, password);
	}

	@Override
	public List<Asesor> buscarPorSector(String sector) {
		 return asesorRespository.findBySectorIgnoreCase(sector);
	}

	@Override
	public List<Asesor> buscarPorCarrera(String carrera) {
		return asesorRespository.findByCarreraIgnoreCase(carrera);
	}
	
	@Override
	public Optional<Asesor> findByEmail(String email) {
		return asesorRespository.findByEmail(email);
	}

	@Override
	public void updatePassword(String email, String nuevaPassword) {
		 Optional<Asesor> asesorOpt = asesorRespository.findByEmail(email);
		    if (asesorOpt.isPresent()) {
		        Asesor asesor = asesorOpt.get();
		        asesor.setPassword(nuevaPassword);
		        asesorRespository.save(asesor);
		    } else {
		        throw new RuntimeException("Correo no registrado.");
		    }
		
	}

	 
}
