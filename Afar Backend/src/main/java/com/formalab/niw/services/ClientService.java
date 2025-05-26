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

import com.formalab.niw.entities.Client;
import com.formalab.niw.repositories.AppUserRepository;
import com.formalab.niw.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository ;
	
	@Autowired
	AppUserRepository appUserRepository ;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	public <S extends Client> S save(S entity) {
		return clientRepository.save(entity);
	}

	public  String RegisterClient (Client client) {
		
		if (appUserRepository.existsByEmail(client.getEmail())) {
			return "{\"error\" : \"client with email : " + client.getEmail() + "alrady exists\" }" ;
		}else {
			String hashPW=bCryptPasswordEncoder.encode(client.getPassword());
			client.setPassword(hashPW);
			clientRepository.save(client);
			
			return "{\"message\" : \"success\" }";
 		}
		
	}
	
	public <S extends Client> Optional<S> findOne(Example<S> example) {
		return clientRepository.findOne(example);
	}

	public Page<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public List<Client> findAll(Sort sort) {
		return clientRepository.findAll(sort);
	}

	public List<Client> findAllById(Iterable<Long> ids) {
		return clientRepository.findAllById(ids);
	}

	public <S extends Client> List<S> saveAll(Iterable<S> entities) {
		return clientRepository.saveAll(entities);
	}

	public Optional<Client> findById(Long id) {
		return clientRepository.findById(id);
	}

	public void flush() {
		clientRepository.flush();
	}

	public <S extends Client> S saveAndFlush(S entity) {
		return clientRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return clientRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Client> entities) {
		clientRepository.deleteInBatch(entities);
	}

	public <S extends Client> Page<S> findAll(Example<S> example, Pageable pageable) {
		return clientRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		clientRepository.deleteAllInBatch();
	}

	public Client getOne(Long id) {
		return clientRepository.getOne(id);
	}

	public <S extends Client> long count(Example<S> example) {
		return clientRepository.count(example);
	}

	public <S extends Client> boolean exists(Example<S> example) {
		return clientRepository.exists(example);
	}

	public <S extends Client> List<S> findAll(Example<S> example) {
		return clientRepository.findAll(example);
	}

	public long count() {
		return clientRepository.count();
	}

	public void deleteById(Long id) {
		clientRepository.deleteById(id);
	}

	public <S extends Client> List<S> findAll(Example<S> example, Sort sort) {
		return clientRepository.findAll(example, sort);
	}

	public void delete(Client entity) {
		clientRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Client> entities) {
		clientRepository.deleteAll(entities);
	}

	public void deleteAll() {
		clientRepository.deleteAll();
	} 
	
	
	
	
}
