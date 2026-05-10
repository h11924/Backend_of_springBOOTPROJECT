package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
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
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();

    }

}
