package com.formalab.niw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.Admin;
import com.formalab.niw.repositories.AppUserRepository;
import com.formalab.niw.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository ;
	
	@Autowired
	AppUserRepository appUserRepository ;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	public <S extends Admin> S save(S entity) {
		return adminRepository.save(entity);
	}

	public  String RegisterAdmin (Admin admin) {
		
		if (appUserRepository.existsByEmail(admin.getEmail())) {
			return "{\"error\" : \"admin with email : " + admin.getEmail() + "alrady exists\" }" ;
		}else {
			String hashPW=bCryptPasswordEncoder.encode(admin.getPassword());
			admin.setPassword(hashPW);
			adminRepository.save(admin);
			
			return "{\"message\" : \"success\" }";
 		}
		
	}
	
	public <S extends Admin> Optional<S> findOne(Example<S> example) {
		return adminRepository.findOne(example);
	}

	public Page<Admin> findAll(Pageable pageable) {
		return adminRepository.findAll(pageable);
	}

	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	public List<Admin> findAll(Sort sort) {
		return adminRepository.findAll(sort);
	}

	public List<Admin> findAllById(Iterable<Long> ids) {
		return adminRepository.findAllById(ids);
	}

	public <S extends Admin> List<S> saveAll(Iterable<S> entities) {
		return adminRepository.saveAll(entities);
	}

	public Optional<Admin> findById(Long id) {
		return adminRepository.findById(id);
	}

	public void flush() {
		adminRepository.flush();
	}

	public <S extends Admin> S saveAndFlush(S entity) {
		return adminRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return adminRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Admin> entities) {
		adminRepository.deleteInBatch(entities);
	}

	public <S extends Admin> Page<S> findAll(Example<S> example, Pageable pageable) {
		return adminRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		adminRepository.deleteAllInBatch();
	}

	public Admin getOne(Long id) {
		return adminRepository.getOne(id);
	}

	public <S extends Admin> long count(Example<S> example) {
		return adminRepository.count(example);
	}

	public <S extends Admin> boolean exists(Example<S> example) {
		return adminRepository.exists(example);
	}

	public <S extends Admin> List<S> findAll(Example<S> example) {
		return adminRepository.findAll(example);
	}

	public long count() {
		return adminRepository.count();
	}

	public void deleteById(Long id) {
		adminRepository.deleteById(id);
	}

	public <S extends Admin> List<S> findAll(Example<S> example, Sort sort) {
		return adminRepository.findAll(example, sort);
	}

	public void delete(Admin entity) {
		adminRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Admin> entities) {
		adminRepository.deleteAll(entities);
	}

	public void deleteAll() {
		adminRepository.deleteAll();
	} 
	
	
	
	
}
