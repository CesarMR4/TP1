package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import upc.edu.pe.entities.Puntuacion;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Integer> {
    
    List<Puntuacion> findByAsesorId_Asesor(int idAsesor);
    
    @Query("SELECT AVG(p.puntuacion) FROM Puntuacion p WHERE p.asesor.id_Asesor = ?1")
    Double obtenerPromedioPorAsesor(int idAsesor);
}
