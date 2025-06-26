package upc.edu.pe.serviceinterface;


import java.util.Optional;



import upc.edu.pe.entities.Curriculum;
import upc.edu.pe.entities.Reserva;


public interface CurriculumService {
    void guardarCurriculum(Curriculum curriculum);
    Optional<Curriculum> buscarPorReserva(Reserva reserva);
}