package com.charlenry.produits.restcontrollers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.charlenry.produits.dto.ProduitDTO;
import com.charlenry.produits.entities.Image;
import com.charlenry.produits.service.ImageService;
import com.charlenry.produits.service.ProduitService;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRESTController {
  
  @Autowired
  ImageService imageService;

  @Autowired
  ProduitService produitService;


  // http://localhost:8080/produits/api/image/getInfo/{id}
  @GetMapping("/getInfo/{id}")
  public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
    return imageService.getImageDetails(id);
  }

  // http://localhost:8080/produits/api/image/load/{id}
  @GetMapping("/load/{id}")
  public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
    return imageService.getImage(id);
  }
  
  // http://localhost:8080/produits/api/image/getImagesProd/{idProd}
  @GetMapping("/getImagesProd/{idProd}")
  public List<Image> getImagesProd(@PathVariable("idProd") Long idProd)
      throws IOException {
    return imageService.getImagesParProd(idProd);
  }
  
  // http://localhost:8080/produits/api/image/upload
  @PostMapping("/upload")
  public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
    return imageService.uploadImage(file);
  }
  

  // http://localhost:8080/produits/api/image/uploadImageProd/{idProd}
  @PostMapping("/uploadImageProd/{idProd}")
  public Image uploadMultiImages(@RequestParam("image") MultipartFile file,
      @PathVariable("idProd") Long idProd)
      throws IOException {
    return imageService.uploadImageProd(file, idProd);
  }
  
  // http://localhost:8080/produits/api/image/update
  @PutMapping("/update")
  public Image UpdateImage(@RequestParam("image") MultipartFile file) throws IOException {
    return imageService.uploadImage(file);
  }

  // http://localhost:8080/produits/api/image/delete/{id}
  @DeleteMapping("/delete/{id}")
  public void deleteImage(@PathVariable("id") Long id) {
    imageService.deleteImage(id);
  }

 
  /**
   * The `uploadImageFS` method in the `ImageRESTController` class is responsible for uploading an image file to the
   *  file system (FS) along with associating it with a specific product identified by its `id`.
   * 
   * System.getProperty("user.home"): This method is used to get the value of the "user.home" system property. 
   * This property represents the user's home directory, which is typically the directory of the currently logged in user.
   * 
   * "/development/produits/images/" + p.getImagePath(): This is a string that represents the path of the file where 
   * the data will be written. It is constructed by appending the product's image path (p.getImagePath()) to the base 
   * directory path ("/development/produits/images/").
   * 
   * Paths.get(...): This method is used to create a Path object that represents the path of the file where the data 
   * will be written. It takes as input the string that represents the file path.
   * 
   * file.getBytes(): This method is used to get the data that will be written to the file. It converts the file object 
   * into a byte array (byte[]).
   * 
   * Files.write(...): This method is used to write the data to the file. It takes as input the Path object that represents 
   * the file path and the byte array that contains the data to be written.
   */
  // http://localhost:8080/produits/api/image/uploadFS/{id}
  @PostMapping("/uploadFS/{id}")
  public void uploadImageFS(@RequestParam("image") MultipartFile file, @PathVariable("id") Long id) throws IOException {
    ProduitDTO p = produitService.getProduit(id);

    p.setImagePath(id + ".jpg");

    Files.write(Paths.get(System.getProperty("user.home") + "/development/produits/images/" + p.getImagePath()), 
      file.getBytes());

    produitService.saveProduit(p);
  }

  
  /**
   * The `public byte[] getImageFS(@PathVariable("id") Long id) throws IOException` method in the `ImageRESTController` 
   * class is responsible for retrieving and returning the image data as a byte array from the file system (FS) based 
   * on the provided `id`.
   * 
   * @GetMapping: This annotation is used to map HTTP GET requests to a specific controller method. In other words, 
   * when the server receives a GET request at the specified URL, this method will be executed.
   * 
   * produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE: This defines the media type of the response. 
   * In this case, the method is expected to produce a JPEG image in response to the GET request.
   * 
   * Files.readAllBytes(...): This method reads all bytes from the file at the specified path and returns the data 
   * as a byte array. It takes as input the Path object that represents the file path.
   */
  // http://localhost:8080/produits/api/image/loadFromFS/{id}
  @GetMapping(value = "/loadFromFS/{id}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
  public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {
    ProduitDTO p = produitService.getProduit(id);    
    return Files
        .readAllBytes(Paths.get(System.getProperty("user.home") + "/development/produits/images/" + p.getImagePath()));
  }
  
}
