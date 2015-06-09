package biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Socio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int numSocio;
	
	@Column
	private String nombre;
	
	@Column
	private String dni;
	
	@Column
	private String password;
	
	@Column
	private boolean baja;
	
	@OneToMany(mappedBy="socio") //COSADELPROFE ACORDARSE DE ESTO
	private List<Prestamo> Prestamos;
	
	
	public Socio(){
		this(null,null,null);
	}
	
	public List<Prestamo> getPrestamos() {
		if (Prestamos.equals(null)){
			Prestamos = new ArrayList<Prestamo>();
		}
		return Prestamos;
	}
	public Socio (String nombre, String password, String Dni){
		setNombre(nombre);
		setPass(password);
		setDni(Dni);
		setBaja(false);
	}

	public void setBaja(boolean b) {
		this.baja = b;
		
	}

	public void setDni(String dni) {
		this.dni = dni;
		
	}

	public void setPass(String password) {
		this.password = password;
		
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		
	}

	public String getDni() {
		return this.dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getNumSocio() {
		return this.numSocio;
	}

	public boolean getBaja() {
		return this.baja;
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
}
