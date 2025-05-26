package com.formalab.niw.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.RequestEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.AppUser;
import com.formalab.niw.entities.Entreprise;
import com.formalab.niw.entities.EntrepriseRequest;
import com.formalab.niw.entities.Request;
import com.formalab.niw.repositories.EntrepriseRequestRepository;
import com.formalab.niw.repositories.RequestRepository;

@Service
public class RequestService {

	@Autowired
	RequestRepository requestRepository ;

	@Autowired
	EntrepriseRequestRepository entrepriseRequestRepository ; 
	
	@Autowired
	EntrepriseService entrepriseService ; 
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	public Request saveRequestEntreprise(Entreprise newData ,Long idEntreprise) throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		Request request = new Request() ;
		
		System.out.println("NewDara       * " + newData.getEmail());
		EntrepriseRequest er = new EntrepriseRequest() ;
		
		
			EntrepriseRequest.copyObject(newData,er) ;
		
		
		System.out.println("Cloned ER        *" + er.toString());
		
		er.setName(newData.getName());
		
		Entreprise entreprise = entrepriseService.findById(idEntreprise).get() ;
		
		request.setModifiedEntreprise(er);
		request.setValid(false) ;
		
		request.setEntreprise(entreprise) ;
		
		
		entreprise.getRequests().add(request);
		request.setStatus("waiting");
		requestRepository.save(request);
		
		
		
		return request;
	}

	public  String validate(Long idRequest) throws CloneNotSupportedException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		Request request = findById(idRequest);
		
		request.setValid(true);
		request.setStatus("Approuved");
		
		Long id = request.getEntreprise().getId() ;
		
		Entreprise entreprise = entrepriseService.findById(id).get() ;
		
		EntrepriseRequest ER = request.getModifiedEntreprise() ; 
		
		AppUser.copyObject(ER, entreprise);
		entreprise.setName(request.getModifiedEntreprise().getName()) ;
		
		
		entrepriseService.update(entreprise);

		requestRepository.save(request) ;

		return "{\"message\" : \"success\" }" ;
	}
	
	public String Cancel (Long id){
			
		Request request = requestRepository.findById(id).get();
		request.setStatus("Canceled");
		requestRepository.save(request);
		return "{\"message\" : \"success\" }" ;
	} 
	
	public static void copyObject(Object src, Object dest)
	        throws IllegalArgumentException, IllegalAccessException,
	        NoSuchFieldException, SecurityException {
		
		 for (Field field : src.getClass().getDeclaredFields()) {
		    	field.setAccessible(true);
		    	
		    	
		    	if (field.getName()!="imageFile" && field.getName()!="id" &&field.getName()!="Adress") {
		    		dest.getClass().getDeclaredField(field.getName()).setAccessible(true);
		    		
		    		dest.getClass().getDeclaredField(field.getName()).set(dest, field.get(src));
		    	}
		    	
		    	
		    }
	
	}
	
//	public  void copyObject(Object src, Object dest)
//	        throws IllegalArgumentException, IllegalAccessException,
//	        NoSuchFieldException, SecurityException {
//		System.out.println("***** clone methode *****" );
//		System.out.println( src.getClass().getFields().toString());
//		
//	    for (Field srcfield : src.getClass().getFields()) {
//	    	for (int i = 0 , i < src.getClass().getDeclaredFields().length , i++  ) {
//	    		
//	    	}  
//	    	
//	    	
//	    	
//	    	for (Field destfield : src.getClass().getFields()) {
//	    	  destfield.se
//	    	System.out.println("*****Filed" +srcfield);
//	    	if (srcfield.equals(destfield)) {
//	    		destfield.set(srcfield, srcfield);
//	    	 //dest.getClass().getField(field.getName()).set(dest, field.get(src));
//	    	}
//	     
//	    }
//	    	
//	    }
//	}
	
	public <S extends Request> S save(S entity) {
		return requestRepository.save(entity);
	}

	public <S extends Request> Optional<S> findOne(Example<S> example) {
		return requestRepository.findOne(example);
	}

	public Page<Request> findAll(Pageable pageable) {
		return requestRepository.findAll(pageable);
	}

	public List<Request> findAll() {
		return requestRepository.findAll();
	}

	public List<Request> findAll(Sort sort) {
		return requestRepository.findAll(sort);
	}

	public List<Request> findAllById(Iterable<Long> ids) {
		return requestRepository.findAllById(ids);
	}

	public <S extends Request> List<S> saveAll(Iterable<S> entities) {
		return requestRepository.saveAll(entities);
	}

	public Request findById(Long id) {
		return requestRepository.findById(id).get();
	}

	public void flush() {
		requestRepository.flush();
	}

	public <S extends Request> S saveAndFlush(S entity) {
		return requestRepository.saveAndFlush(entity);
	}

	public boolean existsById(Long id) {
		return requestRepository.existsById(id);
	}

	public void deleteInBatch(Iterable<Request> entities) {
		requestRepository.deleteInBatch(entities);
	}

	public <S extends Request> Page<S> findAll(Example<S> example, Pageable pageable) {
		return requestRepository.findAll(example, pageable);
	}

	public void deleteAllInBatch() {
		requestRepository.deleteAllInBatch();
	}

	public Request getOne(Long id) {
		return requestRepository.getOne(id);
	}

	public <S extends Request> long count(Example<S> example) {
		return requestRepository.count(example);
	}

	public <S extends Request> boolean exists(Example<S> example) {
		return requestRepository.exists(example);
	}

	public <S extends Request> List<S> findAll(Example<S> example) {
		return requestRepository.findAll(example);
	}

	public long count() {
		return requestRepository.count();
	}

	public void deleteById(Long id) {
		requestRepository.deleteById(id);
	}

	public <S extends Request> List<S> findAll(Example<S> example, Sort sort) {
		return requestRepository.findAll(example, sort);
	}

	public void delete(Request entity) {
		requestRepository.delete(entity);
	}

	public void deleteAll(Iterable<? extends Request> entities) {
		requestRepository.deleteAll(entities);
	}

	public void deleteAll() {
		requestRepository.deleteAll();
	} 
	
	
	
}
