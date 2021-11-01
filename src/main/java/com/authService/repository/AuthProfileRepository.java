package com.authService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.authService.entity.AuthRequest;



@Repository
public interface AuthProfileRepository extends MongoRepository<AuthRequest, String>{

	
}
