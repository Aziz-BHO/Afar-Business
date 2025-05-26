package com.formalab.niw.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formalab.niw.entities.Entreprise;
import com.formalab.niw.entities.EntrepriseRequest;
import com.formalab.niw.entities.Request;
import com.formalab.niw.services.RequestService;

@RestController
@RequestMapping(value="request")
public class RequestController {

	@Autowired
	private RequestService requestService ;

	@PostMapping(value="entreprise/{idEntreprise}")
	public  Request  save(@RequestBody Entreprise newData , @PathVariable Long idEntreprise) throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		return requestService.saveRequestEntreprise(newData, idEntreprise); 
	}
	
	@PutMapping(value="/validate/{idRequest}",produces = MediaType.APPLICATION_JSON_VALUE)
	public  String  validate (@PathVariable Long idRequest) throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		return requestService.validate(idRequest);
	}
	
	
	
	@GetMapping(value="")
	public List<Request> findAll() {
		return requestService.findAll();
	}
	@GetMapping(value="/{id}")
	public Request findById(@PathVariable Long id) {
		return requestService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		requestService.deleteById(id);
	} 
	@PutMapping(value="")
	public Request Edit(@RequestBody Request request) {
		
		return requestService.save(request);
	}
	
	@PutMapping(value="/cancel/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public String Edit(@PathVariable Long id ) {
		
		return requestService.Cancel(id);
	}

	
}
