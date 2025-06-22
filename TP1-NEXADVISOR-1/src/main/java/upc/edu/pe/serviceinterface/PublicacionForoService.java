package upc.edu.pe.serviceinterface;

import java.util.List;
import java.util.Optional;

import upc.edu.pe.entities.PublicacionForo;

public interface PublicacionForoService {

    void insert(PublicacionForo publicacion);
    List<PublicacionForo> list();
    void delete(int idPublicacion);
    Optional<PublicacionForo> listById(int idPublicacion);
    List<PublicacionForo> listOrderByFechaDesc();
}