package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Product;
import com.psa.backend.domain.repositories.ProductRepository;
import com.psa.backend.domain.handlers.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Collection<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional =  productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException(String.format("Product with id: %d not found", id));
        }
        return productOptional.get();
    }
}
