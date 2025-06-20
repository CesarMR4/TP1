package upc.edu.pe.serviceinterface;

import java.util.List;

import upc.edu.pe.entities.Respuesta;



public interface RespuestaService {

	   public void insert(Respuesta respuesta );
	   public List<Respuesta> list();
	   public void delete(int id);
	   public void update(Respuesta respuesta);
	   public Respuesta findById(int id);
	   List<Respuesta> listarPorComentario(int idComentario);

}
