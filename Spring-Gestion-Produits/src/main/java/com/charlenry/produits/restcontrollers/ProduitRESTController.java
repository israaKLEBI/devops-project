package com.charlenry.produits.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.charlenry.produits.dto.ProduitDTO;
//import com.charlenry.produits.entities.Produit;
import com.charlenry.produits.service.ProduitService;

// API REST Controller

@RestController
// Pour autoriser n'importe quel serveur client
@CrossOrigin("*")

public class ProduitRESTController {
	@Autowired
	ProduitService produitService;

	// Get all products
	// Adresse : http://localhost:8080/produits/api/all
	@GetMapping("/api/all")
	public List<ProduitDTO> getAllProduits() {
		return produitService.getAllProduits();
	}
	
	// Get a product by ID
	// Adresse : http://localhost:8080/produits/api/getProdById/{idProd}
	@GetMapping("/api/getProdById/{idProd}")
	public ProduitDTO getProduitById(@PathVariable("idProd") Long id) {
		return produitService.getProduit(id);
	}
	
	// Add a product
	// Adresse : http://localhost:8080/produits/api/addProd
	//@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(path = "/api/addProd", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.saveProduit(produitDTO);
	}
	
	// Update all fields in a product
	// Adresse : http://localhost:8080/produits/api/updateProd
	@PutMapping(path = "/api/updateProd", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.updateProduit(produitDTO);
	}
	
	// Delete a product by ID
	// Adresse : http://localhost:8080/produits/api/delProdById/{idProd}
	@DeleteMapping("/api/delProdById/{idProd}")
	public void deleteProduit(@PathVariable("idProd") Long id) {
		produitService.deleteProduitById(id);
	}
	
	// Get products by Category
	// Adresse : http://localhost:8080/produits/api/prodsByCat/{idCat}
	@GetMapping("/api/prodsByCat/{idCat}")
	public List<ProduitDTO> getProduitsByCatId(@PathVariable("idCat") Long idCat) {
		return produitService.findByCategorieIdCat(idCat);
	}	
	
	// Get products by Name
	// Adresse : http://localhost:8080/produits/api/prodsByName/{nom}
	@GetMapping("/api/prodsByName/{nom}")
	public List<ProduitDTO> findByNomProduitContains(@PathVariable("nom") String nom) {
		return produitService.findByNomProduitContains(nom);
	}
	
}
