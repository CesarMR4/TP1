package upc.edu.pe.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Horario;
import upc.edu.pe.repositories.HorarioRepository;
import upc.edu.pe.serviceinterface.HorarioService;

@Service
public class HorarioServiceImpl implements HorarioService{
	
	@Autowired
    private HorarioRepository  pR;
	
	@Override
    public void insert(Horario horario) {
        pR.save(horario);
    }
	
	@Override
	public List<Horario> list() {
		return pR.findAll();
	}
	@Override
	public void delete(int id) {
		 pR.deleteById(id); 
	}
	 @Override
	    public Optional<Horario> ListarId(int idHorario) {
	        return pR.findById(idHorario);
	    }


}
