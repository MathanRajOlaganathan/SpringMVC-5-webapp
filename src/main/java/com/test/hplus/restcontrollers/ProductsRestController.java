package com.test.hplus.restcontrollers;

import com.test.hplus.beans.Product;
import com.test.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
public class ProductsRestController {

    @Autowired
    private ProductRepository productRepository;

//    @GetMapping("/hplus/rest/products")
//    @ResponseBody
//    public List<Product> getProducts()
//    {
//        System.out.println("Inside Rest Product Controller");
//        List<Product> productList = new ArrayList<>();
//        productRepository.findAll().forEach(product -> productList.add(product));
//        return productList;
//    }
    @GetMapping("/hplus/rest/products")
    public ResponseEntity getProductByName(@RequestParam("name") String name)
    {
        List<Product> productList = productRepository.searchByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/hplus/rest/products/{name}")
    public ResponseEntity getProductByPathVariable(@PathVariable("name") String name)
    {
        List<Product> productList = productRepository.searchByName(name);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
