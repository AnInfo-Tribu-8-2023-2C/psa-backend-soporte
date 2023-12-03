package com.example.backend.controllers;

import com.example.backend.domain.entities.Product;
import com.example.backend.domain.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?>  getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        Product newProduct = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newProduct = productService.save(product);
        } catch (DataAccessException e) {
            response.put("message", "Error: Product creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", newProduct);
        response.put("message", "Success: Product created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
