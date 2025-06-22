package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.entities.PublicacionForo;

@Repository
public interface PublicacionForoRepository extends JpaRepository<PublicacionForo, Integer> {
    List<PublicacionForo> findByEstudiante(Estudiante estudiante);
    List<PublicacionForo> findAllByOrderByFechaPublicacionDesc();
}