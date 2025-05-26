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

import com.formalab.niw.entities.Category;
import com.formalab.niw.services.CategoryService;

@RestController
@RequestMapping(value="category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService ;

	@PostMapping(value="")
	public  Category  save(@RequestBody Category category) {
		return categoryService.save(category);
	}
	@GetMapping(value="")
	public List<Category> findAll() {
		return categoryService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Category> findById(@PathVariable Long id) {
		return categoryService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		categoryService.deleteById(id);
	} 
	@PutMapping(value="")
	public Category Edit(@RequestBody Category category) {
		
		return categoryService.save(category);
	}
	
	
}
