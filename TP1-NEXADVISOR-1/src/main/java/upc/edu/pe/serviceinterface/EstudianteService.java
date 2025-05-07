package upc.edu.pe.serviceinterface;

import java.util.List;
import upc.edu.pe.entities.Estudiante;
import java.util.Optional;

public interface EstudianteService {
	
	    public void insert(Estudiante estudiante);
	    List<Estudiante>list();
	    public void delete(int idEstudiante);
		Optional<Estudiante> listId_Estudiante(int idEstudiante);
		public void update(Estudiante estudiante);
		Optional<Estudiante> login(String emails, String contrasena);
}
