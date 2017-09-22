package fr.biblioteque.dao.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "auteur", uniqueConstraints = {@UniqueConstraint(columnNames = { "nom", "prenom" }, name = "ui1_auteur_nom_prenom") })
public class Auteur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_auteur")
	private Integer id;
	private String nom;
	private String prenom;
	private String langue;
	
	@OneToMany(mappedBy="auteur")
	@JsonIgnore
	private Set<Livre> livres;

	public Auteur() {}

	public Auteur(String nom, String prenom, String langue) {
		this.nom = nom;
		this.prenom = prenom;
		this.langue = langue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public Set<Livre> getLivres() {
		return livres;
	}

	public void setLivres(Set<Livre> livres) {
		this.livres = livres;
	}

	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", langue=" + langue + ", livres=" + livres
				+ "]";
	}
		
}
