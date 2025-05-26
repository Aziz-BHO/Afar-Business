package com.formalab.niw.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formalab.niw.entities.Client;
import com.formalab.niw.services.ClientService;

@RestController
@RequestMapping(value="client")
public class ClientController {

	@Autowired
	private ClientService clientService ;

	@PostMapping(value="/register" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public  String  save( @RequestBody Client client) {
		return clientService.RegisterClient(client);
	}
	
	@GetMapping(value="")
	public List<Client> findAll() {
		return clientService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Client> findById(@PathVariable Long id) {
		return clientService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		clientService.deleteById(id);
	} 
	@PutMapping(value="")
	public Client Edit(@RequestBody Client client) {
		
		return clientService.save(client);
	}
	
	
}
