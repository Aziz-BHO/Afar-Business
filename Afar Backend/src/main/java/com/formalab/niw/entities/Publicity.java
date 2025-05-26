package com.formalab.niw.entities;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Publicity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String name ;
	private String videoLink ; 
	private String description ; 
	private int pointToEarn ;
	 
	
	
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command","product"})
	@ManyToOne (cascade = CascadeType.REMOVE)
	private Entreprise entreprise ; 
	 
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command","product"})
	@OneToMany (cascade = CascadeType.REMOVE)
	private Set<Point> pointEarned ;
	
	@JsonIgnore
	  @OneToOne 
	   @JoinColumn(name="fileId") 
	    private com.formalab.niw.fileStorage.File video;

}
