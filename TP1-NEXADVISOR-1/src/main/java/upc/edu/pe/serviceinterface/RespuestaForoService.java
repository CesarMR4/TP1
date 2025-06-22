package upc.edu.pe.serviceinterface;

import java.util.List;
import java.util.Optional;

import upc.edu.pe.entities.PublicacionForo;
import upc.edu.pe.entities.RespuestaForo;

public interface RespuestaForoService {

    void insert(RespuestaForo respuesta);
    List<RespuestaForo> list();
    void delete(int idRespuesta);
    Optional<RespuestaForo> listById(int idRespuesta);
    List<RespuestaForo> findByPublicacionOrderByFechaDesc(PublicacionForo publicacion);
}
