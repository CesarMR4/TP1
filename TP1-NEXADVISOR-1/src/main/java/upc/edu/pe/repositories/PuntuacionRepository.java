package upc.edu.pe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.entities.Reserva;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Integer> {

    Optional<Puntuacion> findByReserva(Reserva reserva);
    @Query("SELECT AVG(p.puntuacion) FROM Puntuacion p WHERE p.reserva.asesor.id = :idAsesor")
    Double obtenerPromedioPorAsesor(@Param("idAsesor") int idAsesor);
}