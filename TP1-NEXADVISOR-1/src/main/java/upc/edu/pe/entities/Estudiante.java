package upc.edu.pe.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private int id;

    @Column(name = "nombre",nullable = false, length = 50)
    private String nombre;

    @Column(name = "email",nullable = false, length = 50)
    private String email;

    @Column(name = "contrasena",nullable = false, length = 50)
    private String password;

    @Column(name = "direccion",nullable = false, length = 50)
    private String direccion;

    @Column(name = "telefono",nullable = false, length = 50)
    private String telefono;

    @Column(name = "carrera", nullable = false, length = 50)
    private String carrera;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false, length = 50)
    private Date fechaRegistro;

    @Column(name = "rol",nullable = false, length = 50)
    private String rol = "estudiante";

    @Lob
    @Column(name = "curriculum", length = 50)
    private byte[] curriculum;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Reserva> reservas;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public byte[] getCurriculum() { return curriculum; }
    public void setCurriculum(byte[] curriculum) { this.curriculum = curriculum; }

    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }

    public Estudiante() {}
}
