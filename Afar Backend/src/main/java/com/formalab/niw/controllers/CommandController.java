package com.formalab.niw.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formalab.niw.entities.Command;
import com.formalab.niw.services.CommandService;

@RestController
@RequestMapping(value="command")
public class CommandController {

	@Autowired
	private CommandService commandService ;

	
	
	@PostMapping(value="client/{idClient}/product/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveCommandProductClient( @PathVariable Long idClient,  @RequestParam Long [] idp) {
		return commandService.saveCommandProductClient( idClient, idp);
	}
	
	
	@PutMapping(value="confirm/{idCommand}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String confirmCommand(@PathVariable Long idCommand) {
		return commandService.confirmCommand(idCommand);
	}
	
	
	@PutMapping(value="cancel/{idCommand}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String cancelCommand(@PathVariable  Long idCommand) {
		return commandService.cancelCommand(idCommand);
	}
	
	
	
	
	@PostMapping(value="")
	public  Command  save(@RequestBody Command command) {
		return commandService.save(command);
	}
	@GetMapping(value="")
	public List<Command> findAll() {
		return commandService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Command> findById(@PathVariable Long id) {
		return commandService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		commandService.deleteById(id);
	} 
	@PutMapping(value="")
	public Command Edit(@RequestBody Command command) {
		
		return commandService.save(command);
	}
	
	
}
