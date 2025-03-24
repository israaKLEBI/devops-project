package com.charlenry.produits.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;

@Entity
/**
 * `@Builder` is a Lombok annotation that generates a builder for the annotated class. 
 * This builder pattern allows you to create instances of the class with a fluent API style, making it easier to 
 * construct objects with many attributes. The generated builder class provides methods to set values for each 
 * attribute and then build the object with those values. This can be particularly useful when dealing with classes 
 * that have a large number of fields or when you want to create immutable objects with a convenient way to initialize 
 * them.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	@Id  // c'est la clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // autoincrémentée par la BDD
	@Column(name = "id_image")  // nom donné à la colonne dans la table
	private Long idImage;
	private String name;
	private String type;
	
	@Column(name = "IMAGE", length = 4048576)  // la valeur de length équivaut à Medium LOB
	@Lob  // BLOB : Binary Large Object (permet de stocker en autres les images)
	private byte[] image;

	/*
	 * L'annotation @JoinColumn est utilisée dans JPA (Java Persistence API) pour spécifier la colonne utilisée pour 
	 * joindre une entité à une autre entité dans une relation.
	 * 
	 * @JoinColumn(name="PRODUIT_ID") indique que la colonne PRODUIT_ID dans la table de l'entité courante est utilisée 
	 *  établir la relation avec une autre entité. Cette colonne contient généralement des clés étrangères qui font 
	 * référence à la clé primaire d'une autre table.
	 * 
	 * Par exemple, si vous avez une entité Image qui est liée à une entité Produit, @JoinColumn(name="PRODUIT_ID") 
	 * pourrait être utilisé dans l'entité Image pour indiquer que la colonne PRODUIT_ID dans la table Image est utilisée 
	 * pour faire référence à l'entité Produit.
	 * 
	 * L'annotation @JsonIgnore est utilisée dans le sérialisation/désérialisation JSON avec la bibliothèque Jackson 
	 * en Java. Elle indique que la propriété annotée doit être ignorée.
	 * 
	 * @JsonIgnore est utilisé pour marquer une propriété afin qu'elle soit ignorée lors de la sérialisation et de la 
	 * désérialisation. Cela signifie que lorsque votre objet Java est converti en JSON (sérialisation), la propriété 
	 * annotée ne sera pas incluse dans le JSON généré. De même, lorsque le JSON est converti en un objet Java 
	 * (désérialisation), la valeur de cette propriété sera ignorée, même si elle est présente dans le JSON.
	 * 
	 * C'est utile lorsque vous ne voulez pas exposer certaines propriétés sensibles ou inutiles dans votre JSON, ou 
	 * lorsque vous voulez éviter des boucles infinies lors de la sérialisation d'objets qui ont des relations circulaires.
	 */
	@ManyToOne
	@JoinColumn (name="PRODUIT_ID")
	@JsonIgnore
	private Produit produit;
}