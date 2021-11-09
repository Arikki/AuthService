package com.authService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authService.entity.AuthRequest;
import com.authService.repository.AuthProfileRepository;
import com.mongodb.MongoWriteException;


@Service
public class AuthProfileServiceImpl implements AuthProfileService {
	
	@Autowired
	private AuthProfileRepository repository;
	
@Autowired
	private BCryptPasswordEncoder encoder;

Logger logger = LoggerFactory.getLogger(AuthProfileServiceImpl.class);
	

	public AuthRequest addAuthDetails(AuthRequest request) {
		
		request.setPassword(encoder.encode(request.getPassword()));
		
		logger.info("Inside service to insert details of " + request.getEmail());
		
		return repository.insert(request);
		
		
	}

	public AuthRequest getAuthDetails(String email, boolean internalCall) {
		
		logger.info("Fetching details for the user " +  email);
		
		AuthRequest authDetails= repository.findById(email).get();
		
		logger.info("Successfully fetched details for the user " +  email);
		
		if(!internalCall) {
			authDetails.setPassword("");
		}
		return authDetails;
	}
}
