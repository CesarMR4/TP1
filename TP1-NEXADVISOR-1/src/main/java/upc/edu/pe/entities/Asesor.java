package upc.edu.pe.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Asesor")
public class Asesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_asesor") // mantienes el mapeo a la BD
	private int id; // vuelve a llamarse 'id'
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
	@Lob
	@Column(name = "curriculum", nullable = false)
	private byte[] curriculum;
	@Column(name = "sector", nullable = false, length = 25)
    private String sector;
	@Column(name = "carrera", nullable = false, length = 25)
    private String carrera;
	@Column(name = "fecharegistro", nullable = false, length = 25)
	@Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
	@Column(name = "rol", nullable = false, length = 10)
    private String rol = "asesor";
	
	public int getId() {
		return id;
	}
	public void setId(int id_Asesor) {
		this.id = id_Asesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public byte[] getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(byte[] curriculum) {
		this.curriculum = curriculum;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Asesor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Asesor(int id_Asesor, String nombre, String email, String password, String direccion, String telefono,
			byte[] curriculum, String sector, String carrera, Date fechaRegistro, String rol) {
		super();
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
