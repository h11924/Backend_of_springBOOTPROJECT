package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
        byte[] imageFile =product.getImageDate();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

        //we need to learn more about this code now i dont know everything yet


    }


}
