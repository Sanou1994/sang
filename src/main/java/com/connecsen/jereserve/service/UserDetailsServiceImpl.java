package com.connecsen.jereserve.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.connecsen.jereserve.domaine.Utilisateur;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user =accountService.findUserByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		User userNew = new User(
				user.getUsername(),
				user.getPassword(),
				authorities	
				);
		return userNew;
	}

}
