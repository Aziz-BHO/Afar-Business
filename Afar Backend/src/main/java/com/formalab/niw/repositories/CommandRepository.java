package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.Command;
@Repository
public interface CommandRepository  extends JpaRepository<Command, Long>{

}
