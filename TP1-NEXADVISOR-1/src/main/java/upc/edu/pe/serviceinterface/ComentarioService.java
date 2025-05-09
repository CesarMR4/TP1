package upc.edu.pe.serviceinterface;

import java.util.List;

import upc.edu.pe.entities.Comentario;

public interface ComentarioService {

	 void insert(Comentario comentario);                   
	 List<Comentario> listByForoId(int foroId);             
	 void delete(int id);                                
	 void update(Comentario comentario);
}
