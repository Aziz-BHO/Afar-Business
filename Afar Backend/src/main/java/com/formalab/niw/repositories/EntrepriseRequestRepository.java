package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.EntrepriseRequest;

@Repository
public interface EntrepriseRequestRepository extends JpaRepository<EntrepriseRequest, Long> {

	EntrepriseRequest save(EntrepriseRequest newData);

}
