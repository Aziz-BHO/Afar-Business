package com.formalab.niw.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formalab.niw.entities.Publicity;
import com.formalab.niw.services.PublicityService;

@RestController
@RequestMapping(value="publicity")
public class PublicityController {

	@Autowired
	private PublicityService publicityService ;

	@PostMapping(value="entreprise/{idEntreprise}")
	public  Publicity  savePublicityEntreprise(@RequestBody Publicity publicity , @PathVariable Long idEntreprise) {
		return publicityService.savePublicitEntreprise(publicity, idEntreprise);
	}
	
	@GetMapping(value="")
	public List<Publicity> findAll() {
		return publicityService.findAll();
	}
	@GetMapping(value="/{id}")
	public Publicity findById(@PathVariable Long id) {
		return publicityService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		publicityService.deleteById(id);
	} 
	@PutMapping(value="")
	public Publicity Edit(@RequestBody Publicity publicity) {
		
		return publicityService.save(publicity);
	}
	
	
}
