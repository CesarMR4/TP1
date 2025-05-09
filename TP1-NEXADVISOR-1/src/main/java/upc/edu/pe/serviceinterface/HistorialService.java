package upc.edu.pe.serviceinterface;

import java.util.List;
import upc.edu.pe.entities.Historial;

public interface HistorialService {

	public void insert(Historial historial);
	public List<Historial>list();
	public void delete (int id);
	public void update(Historial historial);
	
}
