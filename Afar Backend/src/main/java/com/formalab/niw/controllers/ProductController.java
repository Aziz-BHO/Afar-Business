package com.formalab.niw.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formalab.niw.entities.Product;
import com.formalab.niw.services.ProductService;

@RestController
@RequestMapping(value="product")
public class ProductController {

	@Autowired
	private ProductService productService ;

	@PostMapping(value="entreprise/{idEntreprise}")
	public  Product  saveProduct(@RequestBody Product product, @PathVariable Long idEntreprise  ) {
		return productService.saveProductEntreprise(product, idEntreprise );
	}
	@GetMapping(value="")
	public List<Product> findAll() {
		return productService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Product> findById(@PathVariable Long id) {
		return productService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		productService.deleteById(id);
	} 
	@PutMapping(value="")
	public Product Edit(@RequestBody Product product) {
		
		return productService.save(product);
	}
	
	
}
