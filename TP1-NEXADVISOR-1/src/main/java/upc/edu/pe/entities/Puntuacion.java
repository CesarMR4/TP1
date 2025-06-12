package upc.edu.pe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Puntuacion;

    @Column(nullable = false)
    private int puntuacion;

    @Column(length = 500)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_Asesor", nullable = false)
    private Asesor asesor;

	public Puntuacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Puntuacion(int id_Puntuacion, int puntuacion, String comentario, Asesor asesor) {
		super();
		this.id_Puntuacion = id_Puntuacion;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
		this.asesor = asesor;
	}

	public int getId_Puntuacion() {
		return id_Puntuacion;
	}

	public void setId_Puntuacion(int id_Puntuacion) {
		this.id_Puntuacion = id_Puntuacion;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Asesor getAsesor() {
		return asesor;
	}

	public void setAsesor(Asesor asesor) {
		this.asesor = asesor;
	}

 

  
}
