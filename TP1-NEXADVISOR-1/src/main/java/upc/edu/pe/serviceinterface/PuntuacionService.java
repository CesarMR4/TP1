package upc.edu.pe.serviceinterface;

import java.util.List;

import upc.edu.pe.entities.Puntuacion;

public interface PuntuacionService {
    
    void insert(Puntuacion puntuacion);
    
    List<Puntuacion> list();
    
    List<Puntuacion> listarPorAsesor(int idAsesor);
    
    Double obtenerPromedioPorAsesor(int idAsesor);
}
