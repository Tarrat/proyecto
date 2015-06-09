package biblioteca;

import java.util.List;



public interface LibroDAO {
	
	
	public Libro findByPrimaryKey( int numSerie);
	
	public List<Libro> findByAuthor( String autor );
	
	public List<Libro> findByTittle( String titulo);
	
	public List<Libro> findAll();

	List<Libro> findNotBorrowed();
	
	
}
