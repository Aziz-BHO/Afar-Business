package com.formalab.niw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.Entreprise;
import com.formalab.niw.entities.Point;
import com.formalab.niw.entities.Publicity;
import com.formalab.niw.repositories.PublicityRepository;

@Service
public class PublicityService {

	@Autowired
	PublicityRepository publicityRepository ;

	@Autowired
	EntrepriseService entrepriseService ; 
	
	public  Publicity savePublicitEntreprise (Publicity publicity , Long idEntreprise ) {
		Entreprise entreprise = entrepriseService.findById(idEntreprise).get() ;
		publicity.setEntreprise(entreprise) ;
		publicityRepository.save(publicity);
		entreprise.getPublicities().add(publicity); 
		entrepriseService.save(entreprise);
		
		
		return publicity ;
	}
	
	public <S extends Publicity> S save(S entity) {
		return publicityRepository.save(entity);
	}

	public <S extends Publicity> Optional<S> findOne(Example<S> example) {
		return publicityRepository.findOne(example);
	}

	public Page<Publicity> findAll(Pageable pageable) {
		return publicityRepository.findAll(pageable);
	}

	public List<Publicity> findAll() {
		return publicityRepository.findAll();
	}

	public List<Publicity> findAll(Sort sort) {
		return publicityRepository.findAll(sort);
	}

	public List<Publicity> findAllById(Iterable<Long> ids) {
		return publicityRepository.findAllById(ids);
	}

	public <S extends Publicity> List<S> saveAll(Iterable<S> entities) {
		return publicityRepository.saveAll(entities);
	}

	public Publicity findById(Long id) {
		return publicityRepository.findById(id).get();
	}

	public void flush() {
		publicityRepository.flush();
	}

	public <S extends Publicity> S saveAndFlush(S entity) {
		return publicityRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return publicityRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Publicity> entities) {
		publicityRepository.deleteInBatch(entities);
	}

	public <S extends Publicity> Page<S> findAll(Example<S> example, Pageable pageable) {
		return publicityRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		publicityRepository.deleteAllInBatch();
	}

	public Publicity getOne(Long id) {
		return publicityRepository.getOne(id);
	}

	public <S extends Publicity> long count(Example<S> example) {
		return publicityRepository.count(example);
	}

	public <S extends Publicity> boolean exists(Example<S> example) {
		return publicityRepository.exists(example);
	}

	public <S extends Publicity> List<S> findAll(Example<S> example) {
		return publicityRepository.findAll(example);
	}

	public long count() {
		return publicityRepository.count();
	}

	public void deleteById(Long id) {
		publicityRepository.deleteById(id);
	}

	public <S extends Publicity> List<S> findAll(Example<S> example, Sort sort) {
		return publicityRepository.findAll(example, sort);
	}

	public void delete(Publicity entity) {
		publicityRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Publicity> entities) {
		publicityRepository.deleteAll(entities);
	}

	public void deleteAll() {
		publicityRepository.deleteAll();
	} 
	
}
