package upc.edu.pe.serviceimplements;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Curriculum;
import upc.edu.pe.entities.Reserva;
import upc.edu.pe.repositories.CurriculumRepository;
import upc.edu.pe.serviceinterface.CurriculumService;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;
    
	@Override
	public void guardarCurriculumAnalizado(Curriculum curriculum) {
		// TODO Auto-generated method stub
        Optional<Curriculum> existente = curriculumRepository.findByReserva(curriculum.getReserva());
        if (existente.isPresent()) {
            Curriculum ca = existente.get();
            ca.setTextoCurriculum(curriculum.getTextoCurriculum());
            ca.setReporteIA(curriculum.getReporteIA());
            curriculumRepository.save(ca);
        } else {
            curriculumRepository.save(curriculum);
        }
		
	}

	@Override
	public Optional<Curriculum> buscarPorReserva(Reserva reserva) {
		return curriculumRepository.findByReserva(reserva);
	}

}
