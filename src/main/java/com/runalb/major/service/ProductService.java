package com.runalb.major.service;

import com.runalb.major.model.Product;
import com.runalb.major.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService  {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }


    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void removeProductById(int id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
    }
}
