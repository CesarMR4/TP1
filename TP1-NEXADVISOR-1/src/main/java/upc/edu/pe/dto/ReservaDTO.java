package upc.edu.pe.dto;

import java.util.Date;

public class ReservaDTO {
	private int id;
    private String estado;
    private String comentarioAsesor;
    private String horaReserva;
    private Date fechaReserva;
    private int estudianteId;
    private String estudianteNombre;

    // Constructor completo
    public ReservaDTO(int id, String estado, String comentarioAsesor, String horaReserva, Date fechaReserva, int estudianteId, String estudianteNombre) {
        this.id = id;
        this.estado = estado;
        this.comentarioAsesor = comentarioAsesor;
        this.horaReserva = horaReserva;
        this.fechaReserva = fechaReserva;
        this.estudianteId = estudianteId;
        this.estudianteNombre = estudianteNombre;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getComentarioAsesor() { return comentarioAsesor; }
    public void setComentarioAsesor(String comentarioAsesor) { this.comentarioAsesor = comentarioAsesor; }

    public String getHoraReserva() { return horaReserva; }
    public void setHoraReserva(String horaReserva) { this.horaReserva = horaReserva; }

    public Date getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(Date fechaReserva) { this.fechaReserva = fechaReserva; }

    public int getEstudianteId() { return estudianteId; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }

    public String getEstudianteNombre() { return estudianteNombre; }
    public void setEstudianteNombre(String estudianteNombre) { this.estudianteNombre = estudianteNombre; }
}
