package upc.edu.pe.serviceinterface;

import java.util.List;

import upc.edu.pe.entities.Foro;



public interface ForoService {

	   public void insert(Foro foro );
	   public List<Foro> list();
	   public void delete(int id);
	   public void update(Foro foro);

}
