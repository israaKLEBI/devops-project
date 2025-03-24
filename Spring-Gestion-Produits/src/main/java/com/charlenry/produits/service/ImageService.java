package com.charlenry.produits.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.charlenry.produits.entities.Image;

/**
 * The `public interface ImageService` is defining a contract for a service that deals with images. It declares methods 
 * for uploading an image, getting image details, retrieving an image, and deleting an image. This interface serves as 
 * a blueprint for classes that will implement these methods to provide image-related functionality in the application. 
 */
public interface ImageService {
  Image uploadImage(MultipartFile file) throws IOException;

  Image getImageDetails(Long id) throws IOException;

  /**
   * The method `ResponseEntity<byte[]> getImage(Long id) throws IOException;` in the `ImageService` interface 
   * declares a method signature for retrieving an image in the form of a byte array.
   * 
   * ResponseEntity<byte[]> is a special class in Java Spring used to represent a complete HTTP response, including the 
   * response body, HTTP status, and HTTP headers.
   * 
   * ResponseEntity<byte[]> is used to represent an HTTP response that contains a byte array (byte[]) as the response body. 
   * This is typically used when you want to return a file or an image as an HTTP response. The byte array represents the 
   * content of the file or image.
   * 
   * Using ResponseEntity gives you full control over the HTTP response you return. You can set the HTTP status, add 
   * custom HTTP headers, and include a response body of your choice. In this case, the response body is a byte array.
   */
  ResponseEntity<byte[]> getImage(Long id) throws IOException;

  void deleteImage(Long id);
  
  Image uploadImageProd(MultipartFile file, Long idProd) throws IOException;
  
  List<Image> getImagesParProd(Long prodId);
}
