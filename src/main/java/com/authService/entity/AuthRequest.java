package com.authService.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="authDetails")
public class AuthRequest {

	private String firstName;
	private String lastName;
	private String dateOfBirth;
	@Id
	private String email;
	
	private String password;

}
