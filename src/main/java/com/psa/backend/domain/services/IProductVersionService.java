package com.psa.backend.domain.services;

import com.psa.backend.domain.entities.ProductVersion;
import java.util.Collection;

public interface IProductVersionService {

    public Collection<ProductVersion> getProductVersions(Long productId);

    public ProductVersion getVersionById(Long id);
}
