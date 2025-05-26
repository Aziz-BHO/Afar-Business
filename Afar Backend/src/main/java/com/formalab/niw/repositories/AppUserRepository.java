package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formalab.niw.entities.AppUser; 



public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	boolean existsByEmail(String email) ;
	AppUser findByEmail(String email);
	
}
