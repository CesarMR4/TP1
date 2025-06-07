package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import upc.edu.pe.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

	List<Reserva> findByEstudianteId(int idEstudiante);
	List<Reserva> findByAsesorId(int idAsesor);


}
