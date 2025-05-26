package com.formalab.niw.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Request  {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	
	private boolean isValid ;
	private String Status; 
	
	@OneToOne(cascade = CascadeType.ALL)
	private EntrepriseRequest modifiedEntreprise ;
	
	@ManyToOne 
	private Entreprise entreprise ;
	
	
}
