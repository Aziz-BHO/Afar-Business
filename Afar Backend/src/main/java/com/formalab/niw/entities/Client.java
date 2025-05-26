package com.formalab.niw.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)

@AllArgsConstructor

@Entity
@PrimaryKeyJoinColumn(name ="id")
@Table(name="client") 
public class Client extends AppUser {

	
	
	private String firstName ; 
	private String lastName ; 
	private String cin ;
	
	
	@JsonIgnoreProperties({"pointEarned","points","publicity","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command"})
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
	private Set<Command> commands; 
	
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","TotalpointsPerEntreprise","commands","command","product"})
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL) 
	private Set<Point> points ;
	
	
	@JsonIgnoreProperties({"pointEarned","points","publicity","entreprise","products","client", "pointEarned","totalpointsPerEntreprise","commands","command","product","TotalpointsPerEntreprise"})
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL) 
	private Set<TotalPoint> TotalpointsPerEntreprise ;
	
	public Client () {
		super() ; 
		this.setRole("client") ;
	}


}
