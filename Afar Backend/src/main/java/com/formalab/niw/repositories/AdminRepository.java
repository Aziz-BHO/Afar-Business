package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	
}
