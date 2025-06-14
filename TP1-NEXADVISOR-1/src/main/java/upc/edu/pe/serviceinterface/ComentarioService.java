package upc.edu.pe.serviceinterface;

import java.util.List;

import upc.edu.pe.entities.Comentario;

public interface ComentarioService {

	 void insert(Comentario comentario);                              
	 void delete(int id);                                
	 void update(Comentario comentario);
	 Comentario findById(int id);
	 List<Comentario> listarPorAsesor(int idAsesor);
}
