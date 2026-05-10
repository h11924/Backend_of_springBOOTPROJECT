package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.io.IOException;

@Service

public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){
        return repo.findAll();

    }

    public Product getProductById(int id){
        return repo.findById(id).orElse(null);//by this we can open and see each product
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }
    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setId(id);
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }


}
