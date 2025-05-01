package upc.edu.pe.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Estudiante")
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Estudiante;
	@Column(name = "nombre", nullable = false, length = 25)
	private String nombre;
	@Column(name = "contrasena", nullable = false, length = 25)
	private String contrasena;
	@Column(name = "direccion", nullable = false, length = 50)
	private String direccion;
	@Column(name = "telefono", nullable = false, length = 15)
	private String telefono;
	@Column(name = "curriculum", nullable = false, length = 25)
	private String curriculum;
	@Column(name = "carrera", nullable = false, length = 25)
	private String carrera;
	@Column(name = "fechaRegistro", nullable = false, length = 25)
	private Date fechaRegistro;
	@Column(name = "rol", nullable = false, length = 10)
	private String rol = "estudiante";
	
	public Estudiante () {
		super();
	}
	 public Estudiante(int id_Estudiante, String nombre, String contrasena, String direccion, 
			 String telefono, String curriculum, String carrera, Date fechaRegistro, String rol) {
	         
		 this.id_Estudiante = id_Estudiante;
		 this.nombre = nombre;
		 this.contrasena = contrasena;
		 this.direccion = direccion;
		 this.telefono = telefono;
		 this.curriculum = curriculum;
		 this.carrera = carrera;
		 this.fechaRegistro = fechaRegistro;
		 this.rol = rol;
	    }
	  public int getId_estudiante() {
	        return id_Estudiante;
	    }

	    public void setId_estudiante(int id_Estudiante) {
	        this.id_Estudiante = id_Estudiante;
	    }
	    public String getNombre () {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    public String getContrasena () {
	        return contrasena;
	    }
	    public void setContrasena(String contrasena) {
	        this.contrasena = contrasena;
	    }
	    public String getDireccion () {
	        return direccion;
	    }
	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }
	    public String getTelefono () {
	        return telefono;
	    }
	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }
	    public String getCurriculum () {
	        return curriculum;
	    }
	    public void setCurriculum(String curriculum) {
	        this.curriculum = curriculum;
	    }
	    public String getCarrera () {
	        return carrera;
	    }
	    public void setCarrera(String carrera) {
	        this.carrera = carrera;
	    }
	    public Date getFecha_Registro () {
	        return fechaRegistro;
	    }
	    public void setFecha_Registro(Date fechaRegistro) {
	        this.fechaRegistro = fechaRegistro;
	    }
	    public String getRol () {
	        return rol;
	    }
	    public void setRol(String rol) {
	        this.rol = rol;
	    }
	    
	    
´	

}
