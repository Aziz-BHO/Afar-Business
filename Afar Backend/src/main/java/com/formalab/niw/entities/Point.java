package com.formalab.niw.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Point {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	
	private int earned ;
	
	
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command"})
	@ManyToOne (cascade = CascadeType.ALL)
	private Publicity publicity ; 
	
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command"})
	@ManyToOne(cascade = CascadeType.ALL)
	private Client client ;

	@Override
	public String toString() {
		return "Point [id=" + id + ", earned=" + earned + ", publicity=" + publicity + ", client=" + client + "]";
	}

	
	
	
}
