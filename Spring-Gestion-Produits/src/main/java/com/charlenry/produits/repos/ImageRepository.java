package com.charlenry.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.charlenry.produits.entities.Image;

/**
 * This line of code is defining an interface named `ImageRepository` that extends the `JpaRepository` interface. 
 * The `JpaRepository` interface is a part of the Spring Data JPA framework and provides methods for performing CRUD 
 * operations on entities. In this case, the `ImageRepository` interface will be used to perform CRUD operations on 
 * entities of type `Image`, where the primary key of the `Image` entity is of type `Long`.
 */
public interface ImageRepository extends JpaRepository<Image , Long>{

}
