package upc.edu.pe.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Foro;

import upc.edu.pe.repositories.ForoRepository;
import upc.edu.pe.serviceinterface.ForoService;

@Service
public class ForoServiceImpl implements ForoService{
	
	@Autowired
	private ForoRepository foroRespository;

	@Override
	public void insert(Foro foro) {
		// TODO Auto-generated method stub
		foroRespository.save(foro);
	}

	@Override
	public List<Foro> list() {
		// TODO Auto-generated method stub
		return foroRespository.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		foroRespository.deleteById(id);
	}

}
