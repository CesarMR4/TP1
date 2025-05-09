package upc.edu.pe.serviceimplements;

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
	
	

}
