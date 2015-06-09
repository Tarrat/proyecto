package biblioteca;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class LibroHibernateDAO implements LibroDAO {
	
	private EntityManager em;

	public LibroHibernateDAO( EntityManager em ){
		this.em = em;
	}
	
	@Override
	public Libro findByPrimaryKey(int numSerie) {
		TypedQuery<Libro> q = em.createQuery("from Libro where numSerie=:numSerie", Libro.class);
		q.setParameter("numSerie", numSerie );
		List<Libro> resultList = q.getResultList();
		return resultList.get(0);
	}

	@Override
	public List<Libro> findByAuthor(String autor) {
		TypedQuery<Libro> q = em.createQuery("from Libro where autor=:autor and prestado=:prest", Libro.class);
		q.setParameter("autor", autor );
		q.setParameter("prest", false);
		List<Libro> resultList = q.getResultList();
		return (List<Libro>) resultList;
	}
	
	@Override
	public List<Libro> findByTittle( String titulo) {
		TypedQuery<Libro> q = em.createQuery("from Libro where titulo=:titulo and prestado=:prest", Libro.class);
		q.setParameter("titulo", titulo);
		q.setParameter("prest", false);
		List<Libro> resultList = q.getResultList();
		return (List<Libro>) resultList;
	}

	@Override
	public List<Libro> findAll() {
		TypedQuery<Libro> q = em.createQuery("from Libro", Libro.class);
		List<Libro> resultList = q.getResultList();
		return (List<Libro>) resultList;
	}
	
	@Override
	public List<Libro> findNotBorrowed(){
		TypedQuery<Libro> q = em.createQuery("from Libro where prestado=:prest", Libro.class);
		q.setParameter("prest", false);
		List<Libro> resultList = q.getResultList();
		return resultList;
	}

}
