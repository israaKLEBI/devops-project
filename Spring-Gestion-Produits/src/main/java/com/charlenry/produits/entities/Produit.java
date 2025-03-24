package com.charlenry.produits.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produit")
	private Long idProduit;
	private String nomProduit;
	private Double prixProduit;
	private Date dateCreation;
	
	@ManyToOne // plusieurs produits peuvent appartenir à une seule catégorie
	private Categorie categorie;
	
	/*
	 * mappedBy = "produit" signifie que cette entité est le côté non propriétaire de la relation, et que l'entité propriétaire est 
	 * celle qui a l'attribut produit. En d'autres termes, une autre entité a un attribut produit qui est utilisé pour maintenir 
	 * cette relation de un à plusieurs.
	 * 
	 * Par exemple, si vous avez une entité Image qui a un attribut produit, alors mappedBy = "produit" signifie que chaque 
	 * instance de Produit peut être associée à plusieurs instances de Image, et chaque Image est associée à exactement un Produit. 
	 * L'entité Image est le côté propriétaire de la relation, car elle contient l'attribut produit qui est utilisé pour maintenir
	 *  la relation.
	 */
	@OneToMany(mappedBy = "produit")
	private List<Image> images;
	
	private String imagePath;
	


	public Produit(String nomProduit, Double prixProduit, Date dateCreation) {
		super();
		this.nomProduit = nomProduit;
		this.prixProduit = prixProduit;
		this.dateCreation = dateCreation;
	}

	
	public Long getIdProduit() {
		return idProduit;
	}
	
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	
	public Double getPrixProduit() {
		return prixProduit;
	}
	
	public void setPrixProduit(Double prixProduit) {
		this.prixProduit = prixProduit;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public Produit() {
		super();
	}
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}


	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", prixProduit=" + prixProduit
				+ ", dateCreation=" + dateCreation + ", categorie=" + "]";
	}


	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
