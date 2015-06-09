package biblioteca;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SocioHibernateDAO implements SocioDAO{


	private EntityManager em;

	public SocioHibernateDAO(EntityManager em2) {
		em = em2;
	}

	@Override
	public Socio findByPrimaryKey(Socio s) {
		TypedQuery<Socio> q = em.createQuery("from Socio where numSocio=:numSocio", Socio.class);
		q.setParameter("numSocio", s.getNumSocio() );
		List<Socio> resultList = q.getResultList();
		return resultList.get(0);
	}

	@Override
	public Socio findByUser(String nombre, String pass) {
		TypedQuery<Socio> q = em.createQuery("from Socio where nombre=:nombre and password=:pass", Socio.class);
		q.setParameter("nombre", nombre);
		q.setParameter("pass", pass);
		List<Socio> resultList = q.getResultList();
		if (resultList.isEmpty()){
			return null;
		}
		else return resultList.get(0);
	}

	public List<Socio> findAll(){
		TypedQuery<Socio> q = em.createQuery("from Socio", Socio.class);
		List<Socio> resultList = q.getResultList();
		return resultList;

	}

	@Override
	public Socio findByNombre(String nombre) {
		TypedQuery<Socio> q = em.createQuery("from Socio where nombre=:nombre", Socio.class);
		q.setParameter("nombre", nombre);
		List<Socio> resultList = q.getResultList();
		if (resultList.isEmpty()){
			return null;
		}
		else return resultList.get(0);
	}
	
	@Override
	public List<Socio> findBajas(){
		TypedQuery<Socio> q = em.createQuery("from Socio where baja=:baja", Socio.class);
		q.setParameter("baja", true);
		List<Socio> resultList = q.getResultList();
		if (resultList.isEmpty()){
			return null;
		}
		else return resultList;
	}
}

