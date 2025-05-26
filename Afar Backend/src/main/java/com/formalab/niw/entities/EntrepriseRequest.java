package com.formalab.niw.entities;

import java.lang.reflect.Field;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)

@Entity

@PrimaryKeyJoinColumn(name ="id")
public class EntrepriseRequest  implements java.lang.Cloneable {
	
	
	@Id
	private Long id ; 
	private String Adress ; 
	private String Name ; 
	private String email ; 
	private String password ; 
	private String phone ; 
	private String role;
	private String imageLink  ; 
	
	@JsonBackReference
	  @OneToOne 
	   @JoinColumn(name="fileId") 
	    private com.formalab.niw.fileStorage.File imageFile;
	
	
	public Entreprise entreprise () throws CloneNotSupportedException {
	Entreprise e = (Entreprise) super.clone() ;
	return e ;	
	}




	public EntrepriseRequest(String adress, String name, String email, String password, String phone, String role,
			String image) {
		super();
		Adress = adress;
		Name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.imageLink = image;
	}
	
	public static EntrepriseRequest  copyObject(Object src, Object dest)
	        throws IllegalArgumentException, IllegalAccessException,
	        NoSuchFieldException, SecurityException {
		
		 for (Field field : src.getClass().getSuperclass().getDeclaredFields()) {
		    	field.setAccessible(true);
		    	  
		    	
		    	if (field.getName()!="category"&&field.getName()!="publicities" && field.getName()!="products" && field.getName()!="requests") {
		    		dest.getClass().getDeclaredField(field.getName()).setAccessible(true);
		    		
		    		dest.getClass().getDeclaredField(field.getName()).set(dest, field.get(src));
		    	}
		    	
		    	
		    }
		 
		 
		return (EntrepriseRequest) dest;
	
	}




	public EntrepriseRequest() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Override
	public String toString() {
		return "EntrepriseRequest [id=" + id + ", Adress=" + Adress + ", Name=" + Name + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", role=" + role + ", image=" + imageLink + "]";
	}
	
	
	
}
