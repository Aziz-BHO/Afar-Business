package com.formalab.niw.fileStorage;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.formalab.niw.entities.AppUser;
import com.formalab.niw.entities.Entreprise;
import com.formalab.niw.entities.Point;
import com.formalab.niw.entities.Publicity;

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
public class File {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)

	
	private Integer idFile ; 
	private String fileName ; 
	private String extension ;
	private String url ;
	
	
	@JsonBackReference
	@OneToOne(mappedBy="imageFile")
	private AppUser AppUser ; 
	
	
@JsonIgnore
@JsonBackReference
	@ManyToOne()
    @JoinColumn(name = "idPublicity", nullable = true)
	private Publicity publicity ; 
	
 
	@Override
	public String toString() {
		return "File [idFile=" + idFile + ", fileName=" + fileName + ", extension=" + extension + ", url=" + url + "]";
	} 
	
	
	
}