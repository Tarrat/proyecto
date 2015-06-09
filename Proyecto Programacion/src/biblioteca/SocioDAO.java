package biblioteca;

import java.util.List;


public interface SocioDAO {
	
	public Socio findByPrimaryKey( Socio s );
	public Socio findByUser(String nombre, String pass);
	public Socio findByNombre(String nombre);
	List<Socio> findBajas();
	
}
