package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Estudiante;
import upc.edu.pe.entities.PublicacionForo;
import upc.edu.pe.entities.RespuestaForo;

@Repository
public interface RespuestaForoRepository extends JpaRepository<RespuestaForo, Integer> {
    List<RespuestaForo> findByPublicacion(PublicacionForo publicacion);
    List<RespuestaForo> findByEstudiante(Estudiante estudiante);
    List<RespuestaForo> findByPublicacionOrderByFechaRespuestaDesc(PublicacionForo publicacion);
}
