package com.formalab.niw.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id ;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Command {
	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id  ; 
	private float price ;
	private String status ;
	
	
	@ManyToOne 
	@JoinColumn(name ="idClient" )
	private Client client ; 
	
	@ManyToOne
	@JoinColumn(name ="idProduct" )
	private Product product ;

	@Override
	public String toString() {
		return "Command [id=" + id + ", status=" + status + ", client=" + client + ", product=" + product + "]";
	}
	
	
}