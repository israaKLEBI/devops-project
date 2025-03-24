package com.charlenry.produits.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.charlenry.produits.entities.Categorie;
import com.charlenry.produits.repos.CategorieRepository;


// API REST Controller

@RestController
// Pour autoriser uniquement Angular et React
@CrossOrigin("http://localhost:4200, http://localhost:3000")

public class CategorieRESTController {
	
	@Autowired
	CategorieRepository categorieRepository;
	
	
	// Get all categories
	// Adresse : http://localhost:8080/produits/api/cat
	@GetMapping("/api/cat")
	public List<Categorie> getAllProduits() {
		return categorieRepository.findAll();
	}
	
	// Get a category by ID
	// Adresse : http://localhost:8080/produits/api/cat/{idCat}
	@GetMapping("/api/cat/{idCat}")
	public Categorie getCategorieById(@PathVariable("idCat") Long id) {
		return categorieRepository.findById(id).get();
	}
	
	// Delete a Category by ID
	// Adresse : http://localhost:8080/produits/api/cat/delCatById/{idat}
	@DeleteMapping("/api/cat/delCatById/{idCat}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteCategorie(@PathVariable("idCat") Long id) {
		categorieRepository.deleteById(id);
	}
	
}
