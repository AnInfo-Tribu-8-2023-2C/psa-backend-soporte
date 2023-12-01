package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.Product;

import java.util.Collection;

public interface IProductService {

    public Collection<Product> getProducts();
}
