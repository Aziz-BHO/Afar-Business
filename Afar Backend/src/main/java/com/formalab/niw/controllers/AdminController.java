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

import com.formalab.niw.entities.Admin;
import com.formalab.niw.services.AdminService;
import com.formalab.niw.services.ClientService;

@RestController
@RequestMapping(value="admin")
public class AdminController {

	@Autowired
	private AdminService adminService ;

	@PostMapping(value="/register" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public  String  save( @RequestBody Admin admin) {
		return adminService.RegisterAdmin(admin);
	}
	
	@GetMapping(value="")
	public List<Admin> findAll() {
		return adminService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Admin> findById(@PathVariable Long id) {
		return adminService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		adminService.deleteById(id);
	} 
	@PutMapping(value="")
	public Admin Edit(@RequestBody Admin admin) {
		
		return adminService.save(admin);
	}
	
	
}
