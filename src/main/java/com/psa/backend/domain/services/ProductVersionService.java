package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.ProductVersion;
import com.psa.backend.domain.repositories.ProductVersionRepository;
import com.psa.backend.domain.handlers.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductVersionService implements IProductVersionService {
    @Autowired
    ProductVersionRepository productVersionRepository;

    @Override
    public Collection<ProductVersion> getProductVersions(Long productId) {
        return productVersionRepository.findByProductId(productId);
    }

    @Override
    public ProductVersion getVersionById(Long id) {
        Optional<ProductVersion> versionOptional =  productVersionRepository.findById(id);
        if (versionOptional.isEmpty()) {
            throw new NotFoundException(String.format("Product version with id: %d not found", id));
        }
        return versionOptional.get();
    }
}
