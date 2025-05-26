package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.Entreprise;
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise , Long> {

	Entreprise findByEmail(String email);
	
}
