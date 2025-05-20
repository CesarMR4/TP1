package upc.edu.pe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import upc.edu.pe.entities.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {

	Optional<Estudiante> findByEmailAndPassword(String email, String password);
	Optional<Estudiante> findByEmail(String email);

}
