package com.authService.service;

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
	

	public AuthRequest addAuthDetails(AuthRequest request) {
		
		request.setPassword(encoder.encode(request.getPassword()));
		System.out.println(request.toString());
		
		return repository.insert(request);
		
		
	}

	public AuthRequest getAuthDetails(String email, boolean internalCall) {
		System.out.println("Inside get Auth details() ==>"+ email);	
		AuthRequest authDetails= repository.findById(email).get();
		
		if(!internalCall) {
			authDetails.setPassword("");
		}
		return authDetails;
	}
}
