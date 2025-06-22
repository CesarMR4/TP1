package upc.edu.pe.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "respuesta_foro")
public class RespuestaForo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private int id;

    @Column(name = "contenido", nullable = false, length = 1000)
    private String contenido;

    @Column(name = "fecha_respuesta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;

    @ManyToOne
    @JoinColumn(name = "id_publicacion", nullable = false)
    private PublicacionForo publicacion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

	public RespuestaForo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RespuestaForo(int id, String contenido, Date fechaRespuesta, PublicacionForo publicacion,
			Estudiante estudiante) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.fechaRespuesta = fechaRespuesta;
		this.publicacion = publicacion;
		this.estudiante = estudiante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public PublicacionForo getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(PublicacionForo publicacion) {
		this.publicacion = publicacion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}



}