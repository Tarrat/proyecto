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
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int numSerie;

	@Column
	private String autor;
	
	@Column
	private String titulo;
	
	@Column
	private String sipnosis;
	
	@Column
	private boolean prestado;
	
	@OneToMany(mappedBy="libro")
	private List<Prestamo> Prestamos;
	
	public List<Prestamo> getPrestamos(){
		if (Prestamos.equals(null)){
			Prestamos = new ArrayList<Prestamo>();
		}
		return Prestamos;
	}
	
	public Libro(){
		this(null,null,null);
	}
	
	public Libro(String autor, String titulo, String sipnosis){
		setAutor(autor);
		setTitulo(titulo);
		setSipnosis(sipnosis);
		setPrestado(false);
	}
	void setPrestado(boolean b) {
		this.prestado = b;
		
	}
	
	public void setSipnosis(String sipnosis) {
		this.sipnosis = sipnosis;
		
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		
	}

	public void setAutor(String autor) {
		this.autor = autor;
		
	}
	
	public void setNumSerie(int numSerie){
		this.numSerie = numSerie;
	}
	
	public String getSipnosis() {
		return this.sipnosis;
		
	}

	public String getTitulo() {
		return this.titulo;
		
	}

	public String getAutor() {
		return this.autor;
		
	}
	
	public int getNumSerie(){
		return this.numSerie;
	}
	

	public boolean getPrestado(){
		return this.prestado;
	}

	
	public String toString(){
		return this.getTitulo()+"-"+ this.getAutor();
	}
}
