package com.psa.backend;

import com.psa.backend.domain.dto.MessageDTO;
import com.psa.backend.domain.entities.Product;
import com.psa.backend.domain.entities.ProductVersion;
import com.psa.backend.domain.entities.Ticket;
import com.psa.backend.domain.entities.Task;
import com.psa.backend.domain.entities.Client;
import com.psa.backend.domain.entities.Collaborator;
import com.psa.backend.domain.services.IProductService;
import com.psa.backend.domain.services.IProductVersionService;
import com.psa.backend.domain.services.ITicketService;
import com.psa.backend.domain.services.ITaskService;
import com.psa.backend.domain.services.IClientService;
import com.psa.backend.domain.services.ICollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
public class PsaBackendApplication {

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductVersionService productVersionService;

	@Autowired
	private ITicketService ticketService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IClientService clientService;

	@Autowired
	private ICollaboratorService collaboratorService;

	public static void main(String[] args) {
		SpringApplication.run(PsaBackendApplication.class, args);
	}


// ------------------------------------------------PRODUCTS----------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
	@GetMapping("/products")
	public Collection<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}


// ------------------------------------------PRODUCT VERSIONS--------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
	@GetMapping("/products/{productId}/versions")
	public Collection<ProductVersion> getProductVersions(@PathVariable Long productId) {
		return productVersionService.getProductVersions(productId);
	}

	@GetMapping("/products/{productId}/versions/{id}")
	public ProductVersion getVersionById(@PathVariable Long id) {
		return productVersionService.getVersionById(id);
	}


// -------------------------------------------TICKETS----------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
	@PostMapping("/tickets")
	public Ticket createTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.getTickets();
	}

	@GetMapping("/tickets/{id}")
	public Ticket getTicketById(@PathVariable Long id) {
		return ticketService.getTicketById(id);
	}

	@PutMapping("/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
		try {
			ticketService.getTicketById(id);
			ticket.setId(id);
			ticketService.saveTicket(ticket);
			return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/tickets/{idTicket}/link-task/{idTask}")
	public ResponseEntity<Ticket> linkTask(@PathVariable Long idTicket, @PathVariable Long idTask) {
		try {
			 Ticket ticket = ticketService.getTicketById(idTicket);
			 Task task = taskService.getTaskById(idTask);
			 ticket.getListLinkedTasks().add(task);
			 task.getListLinkedTickets().add(ticket);
			 return ResponseEntity.ok().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// No se si deberian poder borrarse los Tickets
	@DeleteMapping("/tickets/{id}")
	public MessageDTO deleteTicketById(@PathVariable Long id) {
		return ticketService.deleteTicketById(id);
	}


// -----------------------------------------------TASKS--------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------

	@GetMapping("/tasks")
	public Collection<Task> getTasks() {
		return taskService.getTasks();
	}

	//CREATE TASK

	@GetMapping("/tasks/{id}")
	public Task getTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id);
	}

	//SAVE TASK

	@DeleteMapping("/tasks/{id}")
	public MessageDTO deleteTaskById(@PathVariable Long id) {
		return taskService.deleteTaskById(id);
	}


// -------------------------------------------------CLIENTS----------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
	@GetMapping("/clients")
	public Collection<Client> getClients() {
		return clientService.getClients();
	}

	@GetMapping("/clients/{cuit}")
	public Client getClientByCUIT(@PathVariable Long cuit) {
		return clientService.getClientByCUIT(cuit);
	}


// ---------------------------------------------COLLABORATORS--------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
	@GetMapping("/collaborators")
	public Collection<Collaborator> getCollaborators() {
		return collaboratorService.getCollaborators();
	}

	@GetMapping("/collaborators/{id}")
	public Collaborator getCollaboratorById(@PathVariable Long id) {
		return collaboratorService.getCollaboratorById(id);
	}
}

// ------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
