package com.charlenry.produits.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.charlenry.produits.repos.CategorieRepository;


public class CategorieServiceImpl {
	
	@Autowired
	CategorieRepository categorieRepository;

	
	public void deleteCategorieById(Long id) {
		categorieRepository.deleteById(id);
	}
	
}
