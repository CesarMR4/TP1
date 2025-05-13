package upc.edu.pe.serviceinterface;

import java.util.List;
import java.util.Optional;

import upc.edu.pe.entities.Asesor;

public interface AsesorService {

	public void insert(Asesor asesor);
	public List<Asesor>list();
	public void delete (int id);
	Optional<Asesor> listId(int id);
	public void update(Asesor asesor);
	public List<Asesor> buscarPorSector(String sector);
	public List<Asesor> buscarPorCarrera(String carrera);
}
