package upc.edu.pe.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.PublicacionForo;
import upc.edu.pe.entities.RespuestaForo;
import upc.edu.pe.repositories.RespuestaForoRepository;
import upc.edu.pe.serviceinterface.RespuestaForoService;

@Service
public class RespuestaForoServiceImpl  implements RespuestaForoService{

    @Autowired
    private RespuestaForoRepository respuestaRepo;
    
	@Override
	public void insert(RespuestaForo respuesta) {
		// TODO Auto-generated method stub
		   respuestaRepo.save(respuesta);
	}

	@Override
	public List<RespuestaForo> list() {
		// TODO Auto-generated method stub
	       return respuestaRepo.findAll();
	}

	@Override
	public void delete(int idRespuesta) {
		// TODO Auto-generated method stub
	     respuestaRepo.deleteById(idRespuesta);
	}

	@Override
	public Optional<RespuestaForo> listById(int idRespuesta) {
		// TODO Auto-generated method stub
        return respuestaRepo.findById(idRespuesta);
	}

	@Override
	public List<RespuestaForo> findByPublicacionOrderByFechaDesc(PublicacionForo publicacion) {
	    return respuestaRepo.findByPublicacionOrderByFechaRespuestaDesc(publicacion);
	}

}
