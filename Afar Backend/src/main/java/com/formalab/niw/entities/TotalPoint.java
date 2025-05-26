package com.formalab.niw.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author hamza_bramli
 *
 */
@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class TotalPoint {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	private Long idEntreprise ; 
	private int totalpoints ;
	@JsonBackReference
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","product","command"})
	@ManyToOne(cascade = CascadeType.ALL)
	private Client client ;

	

	
	
	
	
}
