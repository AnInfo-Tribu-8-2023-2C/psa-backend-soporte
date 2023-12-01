package com.psa.backend.domain.repositories;

import com.psa.backend.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Override
    List<Ticket> findAll();
}
