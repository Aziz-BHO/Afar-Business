package com.formalab.niw.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formalab.niw.entities.AppUser;
import com.formalab.niw.entities.validator.AppUserService;
import com.formalab.niw.repositories.AppUserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
private AppUserRepository accountService;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

AppUser user = accountService.findByEmail(username);

if (user == null) throw new UsernameNotFoundException(username) ;

Collection<GrantedAuthority>  authorities = new ArrayList<>();
authorities.add(new SimpleGrantedAuthority(user.getRole())) ;
 

return new User(user.getEmail(),user.getPassword(),authorities );
}

}