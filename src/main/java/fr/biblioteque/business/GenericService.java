package fr.biblioteque.business;

import java.util.List;

import fr.biblioteque.dao.entity.Auteur;
import fr.biblioteque.dao.entity.Livre;

public interface GenericService<T> {
	
	List<T> findAll(String queryString, Class<T> entityClass);
	
	T findById(Class<T> entityClass, Integer id);
	
	void insert(T t);
	
	void update(T t);
	
	void delete(T t);
	boolean existLivre(Livre livre);
	boolean existAuteur(Auteur auteur);
}
