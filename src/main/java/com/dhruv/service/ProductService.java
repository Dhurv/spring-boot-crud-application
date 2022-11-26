package com.dhruv.service;

import com.dhruv.model.Product;
import com.dhruv.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
       return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(long id){
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProductsByName(String name){
        return repository.findByName(name);
    }

    public String deleteProduct(long id){
        repository.deleteById(id);
        return "Product with id " + id + " removed from the database";
    }

    public Product updateProduct(Product product){
        Product productFromDB = repository.findById(product.getId()).orElse(null);
        System.out.println(productFromDB);
        assert productFromDB != null;
        productFromDB.setColor(product.getColor());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setName(product.getName());
        return repository.save(productFromDB);
    }
}
