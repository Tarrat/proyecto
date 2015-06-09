package biblioteca;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prestamo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int numeroPrestamo;

	@Column
	private java.util.Date fechaPrestamo;

	@Column
	private java.util.Date fechaEntrega;

	@ManyToOne
	@JoinColumn(name="numSocio") Socio socio;

	@ManyToOne
	@JoinColumn(name="numSerie")
	private Libro libro;

	public Prestamo(){
		this(null);
	}

	public Prestamo (Date fecha){
		setFecha(fecha);
	}

	public void setFecha(Date fecha) {
		this.fechaPrestamo = fecha;

	}

	public void setFechaEntrega(Date fecha){
		this.fechaEntrega = fecha;

	}

	public void setSocio(Socio s){
		this.socio = s;

	}

	public void setLibro(Libro l){
		this.libro = l;
	}

	public Date getFecha(){
		return this.fechaPrestamo;
	}

	public Date getFechaEntrega(){
		return this.fechaEntrega;
	}

	public int getNumPrestamo(){
		return this.numeroPrestamo;
	}	

	public String toString(){
		return this.libro.getTitulo();

	}

	public int getNumSerie(){
		return this.libro.getNumSerie();
	}
	
	public int getNumSocio(){
		return this.socio.getNumSocio();
	}
}
