package upc.edu.pe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upc.edu.pe.entities.Foro;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Integer>{

}
