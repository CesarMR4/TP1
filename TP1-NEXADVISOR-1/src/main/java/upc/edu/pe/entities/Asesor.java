package upc.edu.pe.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Asesor")
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asesor") 
    private int id; 

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "email", nullable = false, length = 25)
    private String email;

    @Column(name = "contrasena", nullable = false, length = 25)
    private String password;

    @Column(name = "direccion", nullable = false, length = 25)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 25)
    private String telefono;

    @Column(name = "sector", nullable = false, length = 25)
    private String sector;

    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "curriculum")
    private byte[] curriculum;

    @Column(name = "carrera", nullable = false, length = 25)
    private String carrera;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecharegistro", nullable = false)
    private Date fechaRegistro;

    @Column(name = "rol", nullable = false, length = 10)
    private String rol = "asesor";

    @OneToMany(mappedBy = "asesor")
    @JsonIgnore
    private List<Reserva> reservas;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id_Asesor) { this.id = id_Asesor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public byte[] getCurriculum() { return curriculum; }
    public void setCurriculum(byte[] curriculum) { this.curriculum = curriculum; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }

    public Asesor() {}

    public Asesor(int id_Asesor, String nombre, String email, String password, String direccion, String telefono,
                  byte[] curriculum, String sector, String carrera, Date fechaRegistro, String rol) {
        this.id = id_Asesor;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.curriculum = curriculum;
        this.sector = sector;
        this.carrera = carrera;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }
}
