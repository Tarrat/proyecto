package biblioteca;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PrestamoHibernateDAO implements PrestamoDAO {

	private EntityManager em;

	public PrestamoHibernateDAO (EntityManager em){
		this.em = em;
	}

	@Override
	public Prestamo findByPrimaryKey(Prestamo p) {
		TypedQuery<Prestamo> q = em.createQuery("from Prestamo where numeroPrestamo=:numeroPrestamo", Prestamo.class);
		q.setParameter("numeroPrestamo", p.getNumPrestamo() );
		List<Prestamo> resultList = q.getResultList();
		return resultList.get(0);
	}

	@Override
	public List<Prestamo> findByDate(Date a, Socio s) {
		TypedQuery<Prestamo> q = em.createQuery("from Prestamo where numSocio = :num and fechaEntrega is null"
				, Prestamo.class);
		//q.setParameter("fecha", a );
		//if (s != null){
		q.setParameter("num", s.getNumSocio());
		//}
		//else q.setParameter("num", -1);
		List<Prestamo> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public List<Prestamo> findAll() {
		TypedQuery<Prestamo> q = em.createQuery("from Prestamo", Prestamo.class);
		List<Prestamo> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<Prestamo> findByNumSerie(int numSerie){
		TypedQuery<Prestamo> q = em.createQuery("from Prestamo where numSerie =:numSerie", Prestamo.class);
		q.setParameter("numSerie", numSerie);
		List<Prestamo> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<Prestamo> findByNumSocio(int numSocio){
		TypedQuery<Prestamo> q = em.createQuery("from Prestamo where numSocio =:numSocio", Prestamo.class);
		q.setParameter("numSocio", numSocio);
		List<Prestamo> resultList = q.getResultList();
		return resultList;
	}

}
