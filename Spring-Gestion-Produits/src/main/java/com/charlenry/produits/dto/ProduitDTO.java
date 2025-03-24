package com.charlenry.produits.dto;

import java.util.Date;
import java.util.List;

import com.charlenry.produits.entities.Categorie;
import com.charlenry.produits.entities.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDTO {
	private Long idProduit;
	private String nomProduit;
	private Double prixProduit;
	private Date dateCreation;
	private Categorie categorie;
	private String nomCat;
	private List<Image> images;
	private String imagePath;
}

