package upc.edu.pe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{
	 List<Comentario> findByForoId(int foroId);
}
