package fr.biblioteque.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="livre",uniqueConstraints={@UniqueConstraint(columnNames = {"titre","id_auteur","date_publication"},name="ui1_livre_auteur_titre_date_pub")})
public class Livre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_livre")
	private Integer id;
	private String titre;
	@Column(name="date_publication")
	private Date datePublication;
	private String description;
	private String categorie;
	
	@ManyToOne
	@JoinColumn(name="id_auteur")
	private Auteur auteur;

	public Livre() {}

	public Livre(String titre, Date datePublication, String description, String categorie, Auteur auteur) {
		this.titre = titre;
		this.datePublication = datePublication;
		this.description = description;
		this.categorie = categorie;
		this.auteur = auteur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", datePublication=" + datePublication + ", description="
				+ description + ", categorie=" + categorie + ", exemplaires=" + "]";
	}
}
