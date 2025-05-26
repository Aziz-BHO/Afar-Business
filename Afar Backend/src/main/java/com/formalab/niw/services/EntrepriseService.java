package com.formalab.niw.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.Category;
import com.formalab.niw.entities.Entreprise;
import com.formalab.niw.repositories.AppUserRepository;
import com.formalab.niw.repositories.EntrepriseRepository;

@Service
public class EntrepriseService {

	@Autowired
	EntrepriseRepository entrepriseRepository ;
	
	@Autowired
	CategoryService categoryService ; 

	@Autowired
	AppUserRepository appUserRepository ;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	
	public  String registerEntreprise (Long idCategory ,  Entreprise  entreprise) {
		
		
		
		if (appUserRepository.existsByEmail(entreprise.getEmail())) {
			return "{\"error\" : \"entreprise with email : " + entreprise.getEmail() + "alrady exists\" }" ;
		}else {
		
			String hashPW=bCryptPasswordEncoder.encode(entreprise.getPassword());
			entreprise.setPassword(hashPW);
		
		Category category = categoryService.findById(idCategory).get() ; 
		System.out.println(category);
		entreprise.setCategory(category);
		category.getEntreprises().add(entreprise) ; 
		entrepriseRepository.save(entreprise) ; 
		categoryService.save(category) ;
		
		return "{\"message\" : \"success\" }";
	}
		
	}
	
	
	public  Entreprise update(Entreprise entity) {
	
		return entrepriseRepository.save(entity);
	}
	
	public  String  Activer(Long id) {
		Entreprise entreprise = entrepriseRepository.findById(id).get();
		entreprise.setStatus("Active");
		entrepriseRepository.save(entreprise) ;
		return "{\"message\" : \"Activated\" }";
	}
	
	public  String  Desactiver(Long id) {
		Entreprise entreprise = entrepriseRepository.findById(id).get();
		entreprise.setStatus("Desactive");
		entrepriseRepository.save(entreprise) ;
		return "{\"message\" : \"Desactivated\" }";
	}
	
	public <S extends Entreprise> S save(S entity) {
		return entrepriseRepository.save(entity);
	}

	public <S extends Entreprise> Optional<S> findOne(Example<S> example) {
		return entrepriseRepository.findOne(example);
	}

	public Page<Entreprise> findAll(Pageable pageable) {
		return entrepriseRepository.findAll(pageable);
	}

	public List<Entreprise> findAll() {
		return entrepriseRepository.findAll();
	}

	public List<Entreprise> findAll(Sort sort) {
		return entrepriseRepository.findAll(sort);
	}

	public List<Entreprise> findAllById(Iterable<Long> ids) {
		return entrepriseRepository.findAllById(ids);
	}

	public <S extends Entreprise> List<S> saveAll(Iterable<S> entities) {
		return entrepriseRepository.saveAll(entities);
	}

	public Optional<Entreprise> findById(Long id) {
		return entrepriseRepository.findById(id);
	}

	public void flush() {
		entrepriseRepository.flush();
	}

	public <S extends Entreprise> S saveAndFlush(S entity) {
		return entrepriseRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return entrepriseRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Entreprise> entities) {
		entrepriseRepository.deleteInBatch(entities);
	}

	public <S extends Entreprise> Page<S> findAll(Example<S> example, Pageable pageable) {
		return entrepriseRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		entrepriseRepository.deleteAllInBatch();
	}

	public Entreprise getOne(Long id) {
		return entrepriseRepository.getOne(id);
	}

	public <S extends Entreprise> long count(Example<S> example) {
		return entrepriseRepository.count(example);
	}

	public <S extends Entreprise> boolean exists(Example<S> example) {
		return entrepriseRepository.exists(example);
	}

	public <S extends Entreprise> List<S> findAll(Example<S> example) {
		return entrepriseRepository.findAll(example);
	}

	public long count() {
		return entrepriseRepository.count();
	}

	public void deleteById(Long id) {
		entrepriseRepository.deleteById(id);
	}

	public <S extends Entreprise> List<S> findAll(Example<S> example, Sort sort) {
		return entrepriseRepository.findAll(example, sort);
	}

	public void delete(Entreprise entity) {
		entrepriseRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Entreprise> entities) {
		entrepriseRepository.deleteAll(entities);
	}

	public void deleteAll() {
		entrepriseRepository.deleteAll();
	} 
	
	
	
}
