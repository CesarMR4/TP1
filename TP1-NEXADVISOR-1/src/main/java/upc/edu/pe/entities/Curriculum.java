package upc.edu.pe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CurriculumAnalizado")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(name = "texto_curriculum", nullable = false)
    private String textoCurriculum;  

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

	public Curriculum(String textoCurriculum, String reporteIA, Reserva reserva) {
		super();
		this.textoCurriculum = textoCurriculum;
		this.reporteIA = reporteIA;
		this.reserva = reserva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextoCurriculum() {
		return textoCurriculum;
	}

	public void setTextoCurriculum(String textoCurriculum) {
		this.textoCurriculum = textoCurriculum;
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