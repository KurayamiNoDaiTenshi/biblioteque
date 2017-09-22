package fr.biblioteque.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.biblioteque.dao.ConnectionEm;
import fr.biblioteque.dao.entity.Auteur;
import fr.biblioteque.dao.entity.Livre;

public class GenericServiceImpl<T> implements GenericService<T> {
	public static EntityManager em = ConnectionEm.getInstance();
	public GenericServiceImpl() {
	}

	public List<T> findAll(String queryString, Class<T> entityClass) {
		 TypedQuery<T> query = em.createQuery(queryString, entityClass); 
	     return query.getResultList();
	}

	public T findById(Class<T> entityClass, Integer id) {
		T t = em.find(entityClass, id);
		return t;
	}

	public void insert(T t) {
		em.getTransaction().begin();
		
		em.persist(t);	
	
		em.getTransaction().commit();
	}
	
	public void update(T t) {
		em.getTransaction().begin();
		
		em.merge(t);
		
		em.getTransaction().commit();
	}
	
	public void delete(T t) {
		em.getTransaction().begin();
		
		em.remove(t);
		
		em.getTransaction().commit();
	}

	@Override
	public boolean existLivre(Livre livre) {
		TypedQuery<Livre> query = em.createQuery("select count(l) from Livre l where titre = :titre and id_auteur = :id_auteur"
				+ "and date_publication = :date_publication",Livre.class);
		query.setParameter("titre", livre.getTitre());
		query.setParameter("id_auteur", livre.getAuteur().getId());
		query.setParameter("date_publication", livre.getDatePublication());
		return query.getMaxResults() != 1;
	}

	@Override
	public boolean existAuteur(Auteur auteur) {
		TypedQuery<Auteur> query = em.createQuery("select count(a) from Auteur a where nom = :nom and prenom = :prenom",Auteur.class);
		query.setParameter("nom", auteur.getNom());
		query.setParameter("prenom",auteur.getPrenom());
		return query.getMaxResults() != 1;
	}
}
