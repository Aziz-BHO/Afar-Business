package com.formalab.niw.entities;

import java.lang.reflect.Field;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.formalab.niw.entities.validator.AppUserService;
import com.formalab.niw.entities.validator.Unique;


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

//Assurer l'hritage des tables Client et entreprise dans une seule table
@Inheritance(strategy=InheritanceType.JOINED)  
public class AppUser {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	private String Adress ; 
	
	private String email ; 
	private String password ; 
	private String phone ; 
	private String role;
	private String imageLink ;
	
	@JsonBackReference
	  @OneToOne 
	   @JoinColumn(name="fileId") 
	    private com.formalab.niw.fileStorage.File imageFile;
	
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", Adress=" + Adress + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", role=" + role + ", image=" + imageLink + "]";
	} 
	
	public static Entreprise  copyObject(Object src, Object dest)
	        throws IllegalArgumentException, IllegalAccessException,
	        NoSuchFieldException, SecurityException {
		
		 for (Field field : src.getClass().getDeclaredFields()) {
		    	field.setAccessible(true);
		    	
		    	
		    	if (field.getName()!="Name" && field.getName()!="category"&&field.getName()!="publicities" && field.getName()!="products" && field.getName()!="requests") {
		    		dest.getClass().getSuperclass().getDeclaredField(field.getName()).setAccessible(true);
		    		
		    		dest.getClass().getSuperclass().getDeclaredField(field.getName()).set(dest, field.get(src));
		    	}
		    	
		    	
		    }

		return (Entreprise) dest;

	}
	
	
}
