package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import upc.edu.pe.entities.Puntuacion;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Integer> {

	List<Puntuacion> findByAsesorId(int idAsesor);
    @Query("SELECT AVG(p.puntuacion) FROM Puntuacion p WHERE p.asesor.id = :idAsesor")
    Double obtenerPromedioPorAsesor(@Param("idAsesor") int idAsesor);
}
