package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.Client;
@Repository
public interface PointRepository  extends JpaRepository<com.formalab.niw.entities.Point, Long>{

	boolean existsPointByClient(Client client) ; 
	
}
