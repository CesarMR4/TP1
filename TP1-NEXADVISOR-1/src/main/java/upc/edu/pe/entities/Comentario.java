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
@Table(name = "Comentario")
public class Comentario {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 @Column(name = "contenido", nullable = false)
	 private String contenido;
	 @Column(name = "fechacreacion", nullable = false)
	 private Date fechacreacion;
	 @ManyToOne
	 @JoinColumn(name = "estudiante_id", referencedColumnName = "id_Estudiante", nullable = false)
	 private Estudiante estudiante;
	 @ManyToOne
	 @JoinColumn(name = "id_asesor", referencedColumnName = "id_asesor") // 👈 clave
	 private Asesor asesor;
	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comentario(int id, String contenido, Date fechacreacion, Estudiante estudiante, Asesor asesor) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.fechacreacion = fechacreacion;
		this.estudiante = estudiante;
		this.asesor = asesor;
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
	public Asesor getAsesor() {
	    return asesor;
	}

	public void setAsesor(Asesor asesor) {
	    this.asesor = asesor;
	}
	
	 
	 
}
