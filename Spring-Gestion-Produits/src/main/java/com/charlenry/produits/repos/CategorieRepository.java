package com.charlenry.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.charlenry.produits.entities.Categorie;

// API Spring DATA REST via JpaRepository

// Fournit toutes les API standard pour l'entité Catégorie dont l'endpoint finit par /cat
// Adresse : http://localhost:8080/produits/cat
@RepositoryRestResource(path = "cat") 

//Pour autoriser uniquement Angular et React ; en l'absence de cette annotation, on obtient une erreur CORS
@CrossOrigin("http://localhost:4200, http://localhost:3000")

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
}
