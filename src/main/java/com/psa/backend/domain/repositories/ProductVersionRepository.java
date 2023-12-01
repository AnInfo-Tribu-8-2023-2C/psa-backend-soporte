package com.psa.backend.domain.repositories;

import com.psa.backend.domain.entities.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long> {
    Collection<ProductVersion> findByProductId(Long productId);
}
