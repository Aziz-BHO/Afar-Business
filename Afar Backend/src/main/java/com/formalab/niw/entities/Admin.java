package com.formalab.niw.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)

@Entity
@PrimaryKeyJoinColumn(name ="id")
@Inheritance(strategy=InheritanceType.JOINED)  
public class Admin extends AppUser {
	
	public Admin() {
		super() ; 
		this.setRole("Admin") ;
	}

}
