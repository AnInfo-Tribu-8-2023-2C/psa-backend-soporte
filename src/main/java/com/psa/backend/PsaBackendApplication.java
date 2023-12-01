package com.psa.backend;

import com.psa.backend.domain.entities.Product;
import com.psa.backend.domain.entities.Ticket;
import com.psa.backend.domain.services.ProductService;
import com.psa.backend.domain.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class PsaBackendApplication {

	@Autowired
	private ProductService productService;

	@Autowired
	private TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(PsaBackendApplication.class, args);
	}

	@GetMapping("/products")
	public Collection<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.getTickets();
	}
}
