package upc.edu.pe.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.PublicacionForo;
import upc.edu.pe.repositories.PublicacionForoRepository;
import upc.edu.pe.serviceinterface.PublicacionForoService;

@Service
public class PublicacionForoServiceImpl implements PublicacionForoService{

	@Autowired
    private PublicacionForoRepository publicacionRepo;
	
	@Override
	public void insert(PublicacionForo publicacion) {
		// TODO Auto-generated method stub
		  publicacionRepo.save(publicacion);
	}

	@Override
	public List<PublicacionForo> list() {
		// TODO Auto-generated method stub
		  return publicacionRepo.findAll();
	}

	@Override
	public void delete(int idPublicacion) {
		// TODO Auto-generated method stub
	       publicacionRepo.deleteById(idPublicacion);
	}

	@Override
	public Optional<PublicacionForo> listById(int idPublicacion) {
		// TODO Auto-generated method stub
		  return publicacionRepo.findById(idPublicacion);
	}

	@Override
	public List<PublicacionForo> listOrderByFechaDesc() {
		// TODO Auto-generated method stub
	    return publicacionRepo.findAllByOrderByFechaPublicacionDesc();
	}

}
