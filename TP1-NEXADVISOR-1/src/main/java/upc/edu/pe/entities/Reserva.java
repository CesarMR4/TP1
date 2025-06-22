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
@Table(name = "Reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fechareserva", nullable = false)
    private Date fechaReserva; 
    @Column(name = "horareserva", nullable = false)
    private String horaReserva; 
    @Column(name = "estado", nullable = false, length = 20)
    private String estado; 
    @Column(name = "comentario_asesor", length = 500)
    private String comentarioAsesor;
    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id_estudiante", nullable = false)
    private Estudiante estudiante;
    @ManyToOne
    @JoinColumn(name = "asesor_id", referencedColumnName = "id_asesor", nullable = false)
    private Asesor asesor;
    @Column(name = "puntuacion")
    private Integer puntuacion;
    
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(int id, Date fechaReserva, String horaReserva, String estado, String comentarioAsesor,
			int puntuacion, Estudiante estudiante, Asesor asesor) {
		super();
		this.id = id;
		this.fechaReserva = fechaReserva;
		this.horaReserva = horaReserva;
		this.estado = estado;
		this.puntuacion = puntuacion;
		this.comentarioAsesor = comentarioAsesor;
		this.estudiante = estudiante;
		this.asesor = asesor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComentarioAsesor() {
		return comentarioAsesor;
	}

	public void setComentarioAsesor(String comentarioAsesor) {
		this.comentarioAsesor = comentarioAsesor;
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


