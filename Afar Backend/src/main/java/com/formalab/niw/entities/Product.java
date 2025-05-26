package com.formalab.niw.entities;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String name ;
	private float price ; 
	private String description ;
	private String imageLink ; 
	
	private int discountPoints ; 
	private float discountPriceperPoints ; 
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command","product"})
	@OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
	private Set<Command> commands; 
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command","product"})
	
	@ManyToOne 
	private Entreprise entreprise ;
	
	
	
	
	
	
	
}
