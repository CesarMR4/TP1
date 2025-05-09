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
@Table (name = "Historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion", nullable = false)
    private String descripcion; 
    @Column(name = "fecha", nullable = false)
    private Date fecha; 
    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id_Estudiante", nullable = false)
    private Estudiante estudiante;
    @ManyToOne
    @JoinColumn(name = "asesor_id", referencedColumnName = "id_Asesor", nullable = false)
    private Asesor asesor;
	public Historial() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Historial(int id, String descripcion, Date fecha, Estudiante estudiante, Asesor asesor) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estudiante = estudiante;
		this.asesor = asesor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
