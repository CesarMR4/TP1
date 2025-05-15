package upc.edu.pe.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Respuesta;

import upc.edu.pe.repositories.RespuestaRepository;
import upc.edu.pe.serviceinterface.RespuestaService;

@Service
public class RespuestaServiceImpl implements RespuestaService{
	
	@Autowired
	private RespuestaRepository respuestaRespository;

	@Override
	public void insert(Respuesta respuesta) {
		// TODO Auto-generated method stub
		respuestaRespository.save(respuesta);
	}

	@Override
	public List<Respuesta> list() {
		// TODO Auto-generated method stub
		return respuestaRespository.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		respuestaRespository.deleteById(id);
	}

	@Override
	public void update(Respuesta respuesta) {
		respuestaRespository.save(respuesta);
		
	}

	@Override
	public Respuesta findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
