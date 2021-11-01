package com.authService.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authService.entity.AuthRequest;
import com.authService.entity.AuthResponse;
import com.authService.service.AuthProfileService;
import com.authService.service.UserService;
import com.authService.utility.JwtUtility;


@RestController
@CrossOrigin
public class HomeController {
	
	@Autowired
	private JwtUtility jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userSvc;
	
	
	@Autowired
	private AuthProfileService authProfileSvc;
	


	@Value("${jwt.token.validity}")
	private  String JWT_TOKEN_VALIDITY;
	
	
	
	
	@PostMapping("/login")
	public AuthResponse auth(@RequestBody AuthRequest authReq) throws Exception {
//		System.out.println("Inside Auth");
//		System.out.println("Auth Request " + authReq.toString());
		try {
		authenticationManager.authenticate(
new UsernamePasswordAuthenticationToken(authReq.getEmail(),authReq.getPassword()));
		}
		catch(AuthenticationException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		
		
		final UserDetails userDetails = userSvc.loadUserByUsername(authReq.getEmail());
		System.out.println("User details object "+userDetails.toString());
		final String token = jwtUtil.generateToken(userDetails);
		final Date tokenExpiresIn = jwtUtil.getExpirationDateFromToken(token);
		
		System.out.println("Token expires is "+ tokenExpiresIn);
		return new AuthResponse( authReq.getEmail(), token, JWT_TOKEN_VALIDITY);
		//return createJwt(authReq.getEmail(),"login");	
	}
	
//	@GetMapping("/getAuthDetails/{id}")
//	public AuthRequest getAuthDetails(@PathVariable ("id") String email) {
//		return authProfileSvc.getAuthDetails(email);
//	}
	
	
	@PostMapping("/signup")
	public AuthResponse signUp(@RequestBody AuthRequest request) {

		
		authProfileSvc.addAuthDetails(request);
		UserDetails userDetails = new User(request.getEmail(),
				request.getPassword(), new ArrayList<>());
		
		System.out.println("User details object "+userDetails.toString());
		final String token = jwtUtil.generateToken(userDetails);

		return new AuthResponse( request.getEmail(), token, JWT_TOKEN_VALIDITY);
		
	}
	
//	private AuthResponse createJwt(String email, String type) {
//		final UserDetails userDetails = userSvc.loadUserByUsername(email);
//		System.out.println("User details object "+userDetails.toString());
//		final String token = jwtUtil.generateToken(userDetails);
//		final Date tokenExpiresIn = jwtUtil.getExpirationDateFromToken(token);
//		System.out.println("Token is "+ token);
//		return new AuthResponse(type, email, token, tokenExpiresIn);
//		
//	}
	
	@GetMapping("/basicDetails/{id}")
	public AuthRequest getAuthDetails(@PathVariable ("id") String email) {
		
		return authProfileSvc.getAuthDetails(email, false);
	}
	

	
	}
