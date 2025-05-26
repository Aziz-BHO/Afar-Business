package com.formalab.niw.entities;

import java.lang.reflect.Field;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
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
@Inheritance(strategy=InheritanceType.JOINED)  
@Table(name="entreprise")  
public class Entreprise extends AppUser implements java.lang.Cloneable {


	private String status;
	private String Name ; 

	@JsonIgnoreProperties("entreprises")
	@ManyToOne 
	private Category category ;
	
	@JsonIgnoreProperties("entreprise")
	@OneToMany(mappedBy = "entreprise" ,cascade = CascadeType.REMOVE)
	private Set<Publicity> publicities ;
	
	@OneToMany(mappedBy = "entreprise" , cascade = CascadeType.REMOVE) 
	private Set<Product> products ;
	
	@JsonIgnoreProperties({"entreprise","newData"})
	@OneToMany(mappedBy = "entreprise",cascade = CascadeType.REMOVE) 
	private Set<Request> requests ;
	
	public Entreprise () {
		super() ; 
		this.setStatus("Desactive");
		this.setRole("entreprise") ;
	}

	public Entreprise(String name) {
		super();
		Name = name;
	}
	
	public EntrepriseRequest entrepriseRequest () throws CloneNotSupportedException {
		EntrepriseRequest e = (EntrepriseRequest)  super.clone() ;
		return e ;	
		}


	@Override
	public String toString() {
		return "Entreprise [Name=" + Name + ", category=" + category + ", publicities=" + publicities + ", products="
				+ products + ", requests=" + requests + "]";
	}
	
	public static Entreprise  copyObject(Object src, Object dest)
	        throws IllegalArgumentException, IllegalAccessException,
	        NoSuchFieldException, SecurityException {
		
		 for (Field field : src.getClass().getDeclaredFields()) {
		    	field.setAccessible(true);
		    	
		    	
		    	if (field.getName()!="category"&&field.getName()!="publicities" && field.getName()!="products" && field.getName()!="requests") {
		    		dest.getClass().getSuperclass().getDeclaredField(field.getName()).setAccessible(true);
		    		
		    		dest.getClass().getSuperclass().getDeclaredField(field.getName()).set(dest, field.get(src));
		    	}
		    	
		    	
		    }

		return (Entreprise) dest;

	}
	
}
