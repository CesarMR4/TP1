package upc.edu.pe.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import upc.edu.pe.entities.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer>{
	List<Horario> findByAsesorId(int idAsesor);
}
