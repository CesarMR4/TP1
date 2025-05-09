package upc.edu.pe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {

}
