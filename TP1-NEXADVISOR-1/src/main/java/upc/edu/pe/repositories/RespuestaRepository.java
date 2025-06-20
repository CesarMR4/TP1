package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer>{

	List<Respuesta> findByComentario_Id(int id);
}
