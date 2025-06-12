package upc.edu.pe.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upc.edu.pe.entities.Puntuacion;
import upc.edu.pe.repositories.PuntuacionRepository;
import upc.edu.pe.serviceinterface.PuntuacionService;

@Service
public class PuntuacionServiceImpl implements PuntuacionService {

    @Autowired
    private PuntuacionRepository pRepo;
    
    @Override
    public void insert(Puntuacion puntuacion) {
        pRepo.save(puntuacion);
    }

    @Override
    public List<Puntuacion> list() {
        return pRepo.findAll();
    }

    @Override
    public List<Puntuacion> listarPorAsesor(int idAsesor) {
        return pRepo.findByAsesorId_Asesor(idAsesor);
    }

    @Override
    public Double obtenerPromedioPorAsesor(int idAsesor) {
        return pRepo.obtenerPromedioPorAsesor(idAsesor);
    }
}
