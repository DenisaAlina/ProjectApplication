package org.fasttrackit.ProjectApp.service;

import org.fasttrackit.ProjectApp.model.Product;
import org.fasttrackit.ProjectApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product  addNewProduct(Product product){
       return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
       return (List<Product>) productRepository.findAll();

    }

    public void deleteProductDetails(Integer productId){
        productRepository.deleteById(productId);
    }
}
