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
	private EstudianteRepository pR;
	
	@Override 
	public void insert(Estudiante estudiante) {
		pR.save(estudiante);
	}
	@Override
	 public List<Estudiante> list() {
        return pR.findAll();
    }
	 @Override
	 public void delete(int idEstudiante) {pR.deleteById(idEstudiante);}
	 
	 /*	 @Override
	public Optional<Estudiante> listId(int id) {
			return pR.findById(id);
		}*/
	@Override
	public Optional<Estudiante> listId_Estudiante(int idEstudiante) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	

}
