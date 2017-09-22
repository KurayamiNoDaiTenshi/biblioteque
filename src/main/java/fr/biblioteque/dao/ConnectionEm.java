package fr.biblioteque.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ConnectionEm {
	
	private static EntityManager em;
	
	private ConnectionEm() {
		em = Persistence.createEntityManagerFactory("biblio").createEntityManager();
	}
	
	public static EntityManager getInstance() {
		if(em == null) {new ConnectionEm();} 
		return em;
	}	
}
