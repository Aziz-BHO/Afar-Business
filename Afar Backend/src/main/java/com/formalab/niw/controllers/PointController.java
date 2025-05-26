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

import com.formalab.niw.entities.Point;
import com.formalab.niw.services.PointService;

@RestController
@RequestMapping(value="point")
public class PointController {

	@Autowired
	private PointService pointService ;

	@PostMapping(value="client/{idclient}/publicity/{IdPublicity}")
	public  Point  AffectPointClientPublicity(@PathVariable Long idclient , @PathVariable Long IdPublicity) {
		return pointService.AffectPointClientPublicity(idclient,IdPublicity );
	}
	
	
	@PostMapping(value="")
	public  Point  save(@RequestBody Point point) {
		return pointService.save(point);
	}
	@GetMapping(value="")
	public List<Point> findAll() {
		return pointService.findAll();
	}
	@GetMapping(value="/{id}")
	public Optional<Point> findById(@PathVariable Long id) {
		return pointService.findById(id);
	}
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable Long id) {
		pointService.deleteById(id);
	} 
	@PutMapping(value="")
	public Point Edit(@RequestBody Point point) {
		
		return pointService.save(point);
	}
	
	
}
