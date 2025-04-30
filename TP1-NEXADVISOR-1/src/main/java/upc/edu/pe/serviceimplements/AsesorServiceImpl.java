package upc.edu.pe.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Asesor;
import upc.edu.pe.repositories.AsesorRepository;
import upc.edu.pe.serviceinterface.AsesorService;

@Service
public class AsesorServiceImpl implements AsesorService {

	@Autowired
	private AsesorRepository asesorRespository;
	@Override
	public void insert(Asesor asesor) {
		// TODO Auto-generated method stub
		asesorRespository.save(asesor);
	}

	@Override
	public List<Asesor> list() {
		// TODO Auto-generated method stub
		return asesorRespository.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		asesorRespository.deleteById(id);
	}

	@Override
	public Optional<Asesor> listId(int id) {
		// TODO Auto-generated method stub
		return asesorRespository.findById(id);
	}

}
