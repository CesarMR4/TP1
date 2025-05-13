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
@Table(name = "Horario")
public class Horario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    @Column(name = "Dia", nullable = false)
    private int dia;
    @Column(name = "horaInicio", nullable = false)
    private Date Hora_inicio;
    @Column(name = "horaFin", nullable = false)
    private Date Hora_fin;
    // DUDA
    @ManyToOne
    @JoinColumn(name = "asesor_id", referencedColumnName = "id_Asesor", nullable = false)
    private Asesor asesor;
    
    public Horario ()
    {
		super();
	}
    
    public Horario (int id, int dia, Date Hora_inicio, Date Hora_fin, Asesor asesor) {
    	super();
    	this.id = id;
    	this.dia = dia;
    	this.Hora_inicio = Hora_inicio;
    	this.Hora_fin = Hora_fin;
    	this.asesor = asesor;
    }
    public int getId() {	
    	return id;
    }
    public void setId(int id) {
		this.id = id;
	}
    public int getDia()
    {	
    	return dia;
    }
    public void setDia(int dia) {
		this.dia = dia;
	}
    public Date getHoraInicio() {
    	return Hora_inicio;
    }
    public void setHoraInicio(Date Hora_inicio) {
    	this.Hora_inicio = Hora_inicio;
    }
    public Date getHoraFin() {
    	return Hora_fin;
    }
    public void setHoraFin(Date Hora_fin) {
    	this.Hora_fin= Hora_fin;
    }
    public Asesor getAsesor() {
		return asesor;
	}

	public void setAsesor(Asesor asesor) {
		this.asesor = asesor;
	} 
    
   
	
}
