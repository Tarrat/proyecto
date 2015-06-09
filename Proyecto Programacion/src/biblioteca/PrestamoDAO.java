package biblioteca;

import java.util.Date;
import java.util.List;

public interface PrestamoDAO {
	
	public Prestamo findByPrimaryKey( Prestamo P);
	
	//public List<Prestamo> findByPartner( Prestamo P );

	public List<Prestamo> findByDate( Date a, Socio s);
	
	public List<Prestamo> findAll();

	List<Prestamo> findByNumSerie(int numSerie);

	List<Prestamo> findByNumSocio(int numSocio);
	

}
