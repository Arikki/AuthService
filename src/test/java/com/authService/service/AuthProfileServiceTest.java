package com.authService.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.authService.entity.AuthRequest;
import com.authService.repository.AuthProfileRepository;

@SpringBootTest
class AuthProfileServiceTest {
	
	@Autowired
	private AuthProfileService authProfileService;
	
	@MockBean
	private AuthProfileRepository repository;
	

	@Test
	void testAddAuthDetails() {
		
		AuthRequest request = new AuthRequest("Rick","Sanchez","1996-11-04","ricky@gmail.com","Ricky@01");
		AuthRequest insertedRequest = new AuthRequest("Rick","Sanchez","1996-11-04","ricky@gmail.com","$2abHQs*");
		BCryptPasswordEncoder encoder = Mockito.mock(BCryptPasswordEncoder.class);
		Mockito.when(encoder.encode(request.getPassword())).thenReturn("$2abHQs*");		
		Mockito.when(repository.insert(request)).thenReturn(insertedRequest);
		
		AuthRequest savedRequest = authProfileService.addAuthDetails(request);
		
		assertEquals(insertedRequest,savedRequest);
		
		
		
		
	}

	@Test	
	void testGetAuthDetails_whichReturnsPasswordAsItIs() {
		String emailId = "ricky@gmail.com";
		AuthRequest savedAuthDetails = new AuthRequest("Rick","Sanchez","1996-11-04","ricky@gmail.com","$2abHQs*");
		Mockito.when(repository.findById(emailId)).thenReturn(Optional.of(savedAuthDetails));
		
		AuthRequest authDetails = authProfileService.getAuthDetails(emailId, true);
	
				assertEquals(savedAuthDetails.getPassword(),authDetails.getPassword());
		
		
	}
	
	@Test	
	void testGetAuthDetails_whichReturnsEmptyPassword() {
		String emailId = "ricky@gmail.com";
		AuthRequest savedAuthDetails = new AuthRequest("Rick","Sanchez","1996-11-04","ricky@gmail.com","$2abHQs*");
		Mockito.when(repository.findById(emailId)).thenReturn(Optional.of(savedAuthDetails));
		
		AuthRequest authDetails = authProfileService.getAuthDetails(emailId, false);
		
		
		
		assertEquals("",authDetails.getPassword());
		
		
		
	}
	
	

}
