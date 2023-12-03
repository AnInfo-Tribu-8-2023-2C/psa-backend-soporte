package com.example.backend.domain.services;

import com.example.backend.domain.entities.ProductVersion;
import com.example.backend.domain.entities.Ticket;
import com.example.backend.domain.services.ITicketService;
import com.example.backend.domain.handlers.NotFoundException;
import com.example.backend.domain.repositories.ProductVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductVersionService implements IProductVersionService {
    @Autowired
    ProductVersionRepository productVersionRepository;

    @Autowired
    ITicketService ticketService;

    @Override
    public List<ProductVersion> getProductVersions(Long productId) {
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

    @Override
    public List<Ticket> getTickets(Long productVersionId) {
        return ticketService.getTickets(productVersionId);
    }
}
