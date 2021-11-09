package com.authService.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authService.entity.AuthRequest;


@Service
public class UserService implements UserDetailsService {
    
	
	
	@Autowired
	private AuthProfileService authProfileSvc;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info(username + " lookup in DB");

		AuthRequest authDetails = authProfileSvc.getAuthDetails(username,true);
		
		
		
		
		return new User(authDetails.getEmail(),authDetails.getPassword(),new ArrayList<>());
		
		

		
	}

}
