package com.formalab.niw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.Category;
import com.formalab.niw.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository ;

	public <S extends Category> S save(S entity) {
		return categoryRepository.save(entity);
	}

	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return categoryRepository.findOne(example);
	}

	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	public List<Category> findAllById(Iterable<Long> ids) {
		return categoryRepository.findAllById(ids);
	}

	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryRepository.saveAll(entities);
	}

	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	public void flush() {
		categoryRepository.flush();
	}

	public <S extends Category> S saveAndFlush(S entity) {
		return categoryRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Category> entities) {
		categoryRepository.deleteInBatch(entities);
	}

	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		categoryRepository.deleteAllInBatch();
	}

	public Category getOne(Long id) {
		return categoryRepository.getOne(id);
	}

	public <S extends Category> long count(Example<S> example) {
		return categoryRepository.count(example);
	}

	public <S extends Category> boolean exists(Example<S> example) {
		return categoryRepository.exists(example);
	}

	public <S extends Category> List<S> findAll(Example<S> example) {
		return categoryRepository.findAll(example);
	}

	public long count() {
		return categoryRepository.count();
	}

	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return categoryRepository.findAll(example, sort);
	}

	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Category> entities) {
		categoryRepository.deleteAll(entities);
	}

	public void deleteAll() {
		categoryRepository.deleteAll();
	}
	
	
	
}
