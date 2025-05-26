package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.Publicity;
@Repository
public interface PublicityRepository extends JpaRepository<Publicity, Long>{

}
