package com.formalab.niw.security;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication ;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.formalab.niw.entities.AppUser;
import com.formalab.niw.entities.Entreprise;
import com.formalab.niw.repositories.AppUserRepository;
import com.formalab.niw.repositories.EntrepriseRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
	String entrepriseStatus = "" ; 
	String entrepriseStatusBodyResp = "" ; 

	private EntrepriseRepository er ;
	
	private AuthenticationManager authenticationManager ;
	private AppUserRepository us;
	
	public JWTAuthentificationFilter(AuthenticationManager authenticationManager , AppUserRepository us , EntrepriseRepository er) {
	super();
	this.us=us ;
	this.authenticationManager = authenticationManager;
	this.er=er;
	}


	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
	AppUser utilisateur = null ;


	try {
	/*recuperation du json envoyer par l'utilisateur pour s'authentifier 
	 et le stocker ou caster dans un objet Utilisateur */
	utilisateur = new ObjectMapper().readValue(request.getInputStream(),AppUser.class ) ;

	} catch (Exception e) {
	throw new RuntimeException(e) ;
	}

	return authenticationManager.authenticate(
	new UsernamePasswordAuthenticationToken(utilisateur.getEmail(), utilisateur.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	Authentication authResult) throws IOException, ServletException {
		
	    User springuser = (User) authResult.getPrincipal();
	    System.out.println(springuser.getUsername());
	 
	    AppUser user = us.findByEmail(springuser.getUsername());
	    
	    if (user.getClass().getName()=="com.formalab.niw.entities.Entreprise") {
        	System.out.println(user.getId());
        	
        	Entreprise entreprise = er.findById(user.getId()).get();
        	
        	System.out.println("RAHI Entreprise");
        	
        	
        	
        	entrepriseStatus = entreprise.getStatus();
        	
        	if (entreprise.getStatus().equalsIgnoreCase("Desactive")) {
        		response.sendError(403);
            	response.setHeader("Authorization", "Compte Desactivé");
        	}else {
        		entrepriseStatusBodyResp = "," + '"'+"status"+'"'  +":" + '"' +entreprise.getStatus() +'"' ;
        	}  
        	 
        	
        	
        } 
	    
	       String jwt = Jwts.builder().setSubject(springuser.getUsername())
	                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
	                .claim("roles", user.getRole()).claim("User_Id", user.getId()).claim("status", entrepriseStatus)
	                .compact();
	        System.out.println(user.getClass().getName());
	        
	        
	        System.out.println(jwt);
	        System.out.println(springuser.getAuthorities().toString());
	        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt  );
	        response.getWriter().append( "{" +
	        		'"' +"Id"+'"' +":" 
	        		+ '"' + user.getId() +'"'
	        		
	        		
	        		);
	        
	       if (entrepriseStatus.equalsIgnoreCase("Active")) {
	    	   response.getWriter().append(entrepriseStatusBodyResp) ;
	       }
	        
	        
	        response.getWriter().append("}") ;
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json");
	        
	}

}
