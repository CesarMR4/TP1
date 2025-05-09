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
@Table(name = "Foro")
public class Foro {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 @Column(name = "titulo", nullable = false)
	 private String titulo; 
	 @Column(name = "horareserva", nullable = false)
	 private String contenido; 
	 @Column(name = "fechacreacion", nullable = false, length = 20)
	 private Date fechacreacion; 
	 @ManyToOne
	 @JoinColumn(name = "estudiante_id", referencedColumnName = "id_Estudiante", nullable = false)
	 private Estudiante estudiante;
	public Foro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Foro(int id, String titulo, String contenido, Date fechacreacion, Estudiante estudiante) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fechacreacion = fechacreacion;
		this.estudiante = estudiante;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	
	
}
