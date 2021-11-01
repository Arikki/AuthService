package com.authService.service;

import com.authService.entity.AuthRequest;

public interface AuthProfileService {

	public AuthRequest addAuthDetails(AuthRequest request);
	public AuthRequest getAuthDetails (String email, boolean internalCall);
	
}
