package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin//this prevents the cors error
@RequestMapping("/api")

public class ProductController {
    @Autowired
    private ProductService service;


    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    //GETTING TO SEE ALL THE PRODUCTS-COMMIT 1
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    //GETTING TO SEE EACH PRODUCT BY CLICK ON THEM-COMMIT 2
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id ){
        return service.getProductById(id);
    }

    //here we are putting/adding a new product in our data base
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
    try {
        Product product1 = service.addProduct(product, imageFile);
        return new ResponseEntity<> (product1,HttpStatus.CREATED);
    }
    catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    //now we will try to see the image here and resolves or uncessary issues as well
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId ){
        Product product=service.getProductById(productId);
        byte[] imageFile =product.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

        //we need to learn more about this code now i dont know everything yet


    }
    //update product
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id ,@RequestPart Product product,@RequestPart MultipartFile imageFile  ) throws IOException {
        Product product1 = service.updateProduct(id,product,imageFile);

        if(product1!= null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);

        else
            return new ResponseEntity<>("Failed to update ",HttpStatus.BAD_REQUEST);
        
    }

    @DeleteMapping("/product/id")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product!=null) {
            service.deleteProduct(id);
            return new ResponseEntity("Deleted",HttpStatus.OK);
        }
        else

            return new ResponseEntity<>("Product not found ",HttpStatus.NOT_FOUND);


    }



}
