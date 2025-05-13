package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Asesor;

@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Integer>{
	List<Asesor> findBySectorIgnoreCase(String sector);
	List<Asesor> findByCarreraIgnoreCase(String carrera);
}
