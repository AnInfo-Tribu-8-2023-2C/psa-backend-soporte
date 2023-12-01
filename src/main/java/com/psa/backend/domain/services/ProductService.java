package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Override
    public Collection<Product> getProducts() {
        return List.of(
            Product.builder()
                .name("Producto 1")
                .description("Description 1")
                .build(),
            Product.builder()
                .name("Producto 2")
                .description("Description 2")
                .build()
        );
    }
}
