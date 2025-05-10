package upc.edu.pe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Asesor;

@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Integer>{
	
}
