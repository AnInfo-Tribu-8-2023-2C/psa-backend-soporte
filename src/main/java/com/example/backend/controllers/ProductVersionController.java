package com.example.backend.controllers;

import com.example.backend.domain.entities.Product;
import com.example.backend.domain.services.IProductService;
import com.example.backend.domain.services.IProductVersionService;
import com.example.backend.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products/{productId}/versions")
public class ProductVersionController {
    @Autowired
    private IProductVersionService productVersionService;

    @Autowired
    private IProductService productService;

    /*@GetMapping
    public ResponseEntity<?> getProductVersions(@PathVariable Long productId) {
        return new ResponseEntity<>(productVersionService.getProductVersions(productId), HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getVersionById(@PathVariable Long id) {
        return new ResponseEntity<>(productVersionService.getVersionById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<?> getTickets(@PathVariable Long id) {
        return new ResponseEntity<>(productVersionService.getTickets(id), HttpStatus.OK);
    }

    /*@PostMapping("/product/save")
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        Product newProduct = null;
        Map<String, Object> response = new HashMap<>();
        try {
            newProduct = productService.save(product);
            System.out.println("newProduct: " + product);
            System.out.println("newProduct name: " + product.getName());
            System.out.println("newProduct: " + product.getId());

        } catch (DataAccessException e) {
            response.put("message", "Error: product creation failed");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("data", newProduct);
        response.put("message", "Success: product created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }*/
}
