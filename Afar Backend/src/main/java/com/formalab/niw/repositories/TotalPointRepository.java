package com.formalab.niw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formalab.niw.entities.TotalPoint;

@Repository
public interface TotalPointRepository extends JpaRepository<TotalPoint, Long> {

}
