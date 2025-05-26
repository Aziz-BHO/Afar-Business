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
import com.formalab.niw.entities.Product;
import com.formalab.niw.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository ;

	@Autowired
	EntrepriseService entrepriseService ;
	public  Product saveProductEntreprise(Product product , Long idEntreprise) {
		Entreprise entreprise = entrepriseService.findById(idEntreprise).get() ;
		product.setEntreprise(entreprise ) ;
		productRepository.save(product) ;
		entreprise.getProducts().add( product) ; 
		entrepriseService.save(entreprise) ;
		return product;
	}
	

	


	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}





	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productRepository.findOne(example);
	}

	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	public List<Product> findAllById(Iterable<Long> ids) {
		return productRepository.findAllById(ids);
	}

	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productRepository.saveAll(entities);
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	public void flush() {
		productRepository.flush();
	}

	public <S extends Product> S saveAndFlush(S entity) {
		return productRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Product> entities) {
		productRepository.deleteInBatch(entities);
	}

	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		productRepository.deleteAllInBatch();
	}

	public Product getOne(Long id) {
		return productRepository.getOne(id);
	}

	public <S extends Product> long count(Example<S> example) {
		return productRepository.count(example);
	}

	public <S extends Product> boolean exists(Example<S> example) {
		return productRepository.exists(example);
	}

	public <S extends Product> List<S> findAll(Example<S> example) {
		return productRepository.findAll(example);
	}

	public long count() {
		return productRepository.count();
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productRepository.findAll(example, sort);
	}

	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Product> entities) {
		productRepository.deleteAll(entities);
	}

	public void deleteAll() {
		productRepository.deleteAll();
	} 
	
	
}
