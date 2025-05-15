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


@Entity
@Table(name = "respuesta")
public class Respuesta {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 @Column(name = "contenido", nullable = false)
	 private String contenido; 
	 @Column(name = "fecha_respuesta", nullable = false, length = 20)
	 private Date fechacreacion; 
	 @ManyToOne
	 @JoinColumn(name = "id_estudiante", referencedColumnName = "id_Estudiante", nullable = false)
	 private Estudiante estudiante;
	 @ManyToOne
	 @JoinColumn(name = "id_comentario", referencedColumnName = "id", nullable = false)
	 private Comentario comentario;
	 
	public Respuesta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Respuesta(int id, String contenido, Date fechacreacion, Estudiante estudiante, Comentario comentario) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.fechacreacion = fechacreacion;
		this.estudiante = estudiante;
		this.comentario = comentario;
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
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	public Comentario getComentario() {
		return comentario;
	}
	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	
	
	
}
