package upc.edu.pe.serviceinterface;

import java.util.List;

import pe.edu.upc.demotopstorefinder.entities.Comprador;
import upc.edu.pe.entities.Estudiante;
import java.util.Optional;

public interface EstudianteService {
	 public void insert(Estudiante estudiante);

	    List<Estudiante>list();

	    public void delete(int idEstudiante);

		Optional<Estudiante> listId_Estudiante(int idEstudiante);
}
