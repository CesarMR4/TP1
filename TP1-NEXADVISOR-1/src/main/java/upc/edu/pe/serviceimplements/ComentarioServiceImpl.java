package upc.edu.pe.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Comentario;
import upc.edu.pe.repositories.ComentarioRepository;
import upc.edu.pe.serviceinterface.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{
	
	@Autowired
    private ComentarioRepository comentarioRepository;

	@Override
	public void insert(Comentario comentario) {
		// TODO Auto-generated method stub
		  comentarioRepository.save(comentario);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		 comentarioRepository.deleteById(id);
	}

	@Override
	public void update(Comentario comentario) {
		// TODO Auto-generated method stub
		   comentarioRepository.save(comentario);
	}

	@Override
	public Comentario findById(int id) {
	    return comentarioRepository.findById(id).orElse(null);
	}

	@Override
	public List<Comentario> listarPorAsesor(int id) {
	    return comentarioRepository.findByAsesor_Id(id);
	}


}
