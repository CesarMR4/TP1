package upc.edu.pe.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "curriculum_analizado")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "reporte_ia", nullable = false, columnDefinition = "TEXT")
    private String reporteIA;

    @OneToOne
    @JoinColumn(name = "reserva_id", referencedColumnName = "id", nullable = false, unique = true)
    private Reserva reserva;

	public Curriculum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curriculum(String reporteIA, Reserva reserva) {
	    this.reporteIA = reporteIA;
	    this.reserva = reserva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReporteIA() {
		return reporteIA;
	}

	public void setReporteIA(String reporteIA) {
		this.reporteIA = reporteIA;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
