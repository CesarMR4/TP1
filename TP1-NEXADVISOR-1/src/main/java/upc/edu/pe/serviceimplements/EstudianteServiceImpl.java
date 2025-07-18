package upc.edu.pe.serviceimplements;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.repositories.EstudianteRepository;
import upc.edu.pe.serviceinterface.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	
	@Autowired
	private EstudianteRepository estudianteRepository;
	
	@Override 
	public void insert(Estudiante estudiante) {
	    if (estudiante.getFechaRegistro() == null) {
	        estudiante.setFechaRegistro(new Date());
	    }
	    estudianteRepository.save(estudiante);
	}
	@Override
	 public List<Estudiante> list() {
        return estudianteRepository.findAll();
    }
	 @Override
	 public void delete(int idEstudiante) {estudianteRepository.deleteById(idEstudiante);}
	 

	@Override
	public Optional<Estudiante> listId_Estudiante(int idEstudiante) {
		// TODO Auto-generated method stub
		return  estudianteRepository.findById(idEstudiante);
	}
	@Override
	public void update(Estudiante estudiante) {
		// TODO Auto-generated method stub
		estudianteRepository.save(estudiante);
	}
	@Override
	public Optional<Estudiante> login(String email, String password) {
		return estudianteRepository.findByEmailAndPassword(email, password);
		
	}
	@Override
	public Optional<Estudiante> findByEmail(String email) {
		 return estudianteRepository.findByEmail(email);
	}
	@Override
	public void updatePassword(String email, String nuevaPassword) {
		  Optional<Estudiante> estudianteOpt = estudianteRepository.findByEmail(email);
		    if (estudianteOpt.isPresent()) {
		        Estudiante estudiante = estudianteOpt.get();
		        estudiante.setPassword(nuevaPassword);
		        estudianteRepository.save(estudiante);
		    } else {
		        throw new RuntimeException("Correo no registrado.");
		    }
		
	}
	@Override
	public Estudiante listarId(int id) {
	    return estudianteRepository.findById(id).orElse(null);
	}

	
	

}
