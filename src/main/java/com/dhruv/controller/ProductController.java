package com.dhruv.controller;

import com.dhruv.model.Product;
import com.dhruv.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    //Default home page loading endpoint
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("create");
    }
    //redirection to Create product page
    @GetMapping("/createProduct")
    public ModelAndView createPage() {
        return new ModelAndView("create");
    }

    //add a single product method
    //tested: working fine
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    //add multiple product at once
    //tested: working fine
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    //get all product object
    //tested: working fine
    @GetMapping("/getProducts")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    //get the product object by id
    //Tested: working fine
    @GetMapping("/getProductById/{id}")
    public ModelAndView findProductById(@PathVariable long id){
        Product product = service.getProductById(id);
        ModelAndView modelAndView =  new ModelAndView("update");
        modelAndView.addObject("product", product);
        return modelAndView;
    }


    //get the product objects by Name
    //Tested: working fine
    @GetMapping("/getProductByName/{name}")
    public List<Product> findProductByName(@PathVariable String name){
        return service.getProductsByName(name);
    }

    //delete a product
    //Tested: working fine
        @DeleteMapping("/delete/{id}")
    public String deleteProduct (@PathVariable long id){
        return service.deleteProduct(id);
    }

    //update the product
    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }
}
