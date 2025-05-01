package upc.edu.pe.entities;


@Entity
@Table(name = "Estudiante")
public class Estudiante {
	
	private int id_Estudiante;
	private String nombre;
	private String contrasena;
	private String direccion;
	private String telefono;
	private String curriculum;
	private Date fechaRegistro;
	

}
