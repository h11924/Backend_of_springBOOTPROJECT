package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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

}
