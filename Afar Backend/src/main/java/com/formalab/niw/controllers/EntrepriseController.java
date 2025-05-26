package com.formalab.niw.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.formalab.niw.services.EntrepriseService;

@RestController
@RequestMapping(value="entreprise")
public class EntrepriseController {

	@Autowired
	private EntrepriseService entrepriseService ;

	@PostMapping(value="")
	public  Entreprise  save(@RequestBody Entreprise entreprise) {
		return entrepriseService.save(entreprise);
	}
	
	@PostMapping(value="register/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	public  String  register(  @RequestBody Entreprise entreprise , @PathVariable Long idCategory  ) {
		return entrepriseService.registerEntreprise(idCategory, entreprise);
	}
	
	@GetMapping(value="")
	public List<Entreprise> findAll() {
		return entrepriseService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Entreprise> findById(@PathVariable Long id) {
		return entrepriseService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		entrepriseService.deleteById(id);
	} 
	@PutMapping(value="")
	public Entreprise Edit(@RequestBody Entreprise entreprise) {
		
		return entrepriseService.save(entreprise);
	}
	@PutMapping(value="activate/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public String activate(@PathVariable Long id) {
		
		return entrepriseService.Activer(id);
	}
	
	@PutMapping(value="desactivate/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public String Desactivate(@PathVariable Long id) {
		
		return entrepriseService.Desactiver(id);
	}
	
}
