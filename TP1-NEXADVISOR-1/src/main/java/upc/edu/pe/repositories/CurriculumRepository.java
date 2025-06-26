package upc.edu.pe.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.entities.Curriculum;
import upc.edu.pe.entities.Reserva;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {
    Optional<Curriculum> findByReserva(Reserva reserva);
    boolean existsByReserva(Reserva reserva);
}