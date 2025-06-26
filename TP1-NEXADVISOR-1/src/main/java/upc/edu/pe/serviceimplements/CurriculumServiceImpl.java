package upc.edu.pe.serviceimplements;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upc.edu.pe.entities.Curriculum;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.repositories.CurriculumRepository;
import upc.edu.pe.serviceinterface.CurriculumService;

@Service
public class CurriculumServiceImpl implements CurriculumService {
	
    @Autowired
    private CurriculumRepository curriculumRepository;

	@Override
	public void guardarCurriculum(Curriculum curriculum) {
		// TODO Auto-generated method stub
        if (curriculumRepository.existsByReserva(curriculum.getReserva())) {
            throw new RuntimeException("Ya existe un currículum para esta reserva");
        }
        curriculumRepository.save(curriculum);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curriculum> buscarPorReserva(Reserva reserva) {
		// TODO Auto-generated method stub
        return curriculumRepository.findByReserva(reserva);
	}


}
