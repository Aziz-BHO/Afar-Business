package com.formalab.niw.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	private String name ; 
	
	@OneToMany(targetEntity = Entreprise.class , mappedBy = "category") 
	private Set<Entreprise> entreprises ; 
	
	
	
}
