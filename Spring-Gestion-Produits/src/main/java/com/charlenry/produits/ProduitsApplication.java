package com.charlenry.produits;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.charlenry.produits.entities.Categorie;
import com.charlenry.produits.entities.Image;
import com.charlenry.produits.entities.Produit;

@SpringBootApplication
public class ProduitsApplication implements CommandLineRunner {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(ProduitsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Expose les id des classes passées en paramètres dans les API Spring DATA REST,
		// c'est-à-dire les API créés de façon standard.
		repositoryRestConfiguration.exposeIdsFor(Produit.class, Categorie.class, Image.class);	
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
