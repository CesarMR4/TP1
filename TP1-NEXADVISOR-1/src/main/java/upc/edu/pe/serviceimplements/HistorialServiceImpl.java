package upc.edu.pe.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Historial;
import upc.edu.pe.repositories.HistorialRepository;
import upc.edu.pe.serviceinterface.HistorialService;

@Service
public class HistorialServiceImpl implements HistorialService{

	@Autowired
	private HistorialRepository historialRepository;
	@Override
	public void insert(Historial historial) {
		// TODO Auto-generated method stub
		historialRepository.save(historial);
	}

	@Override
	public List<Historial> list() {
		// TODO Auto-generated method stub
		return historialRepository.findAll();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		historialRepository.deleteById(id);
	}

	@Override
	public void update(Historial historial) {
		// TODO Auto-generated method stub
		historialRepository.save(historial);
	}

}
