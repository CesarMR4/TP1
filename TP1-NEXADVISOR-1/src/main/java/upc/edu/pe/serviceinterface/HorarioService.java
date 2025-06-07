package upc.edu.pe.serviceinterface;

import java.util.List;
import java.util.Optional;

import upc.edu.pe.entities.Horario;

public interface HorarioService {
	
	 public void insert(Horario horario);
	 public List<Horario> list();
	 public void delete(int id);
	 Optional<Horario> ListarId(int idHorario);
	 List<Horario> findByAsesorId(int idAsesor);


}
