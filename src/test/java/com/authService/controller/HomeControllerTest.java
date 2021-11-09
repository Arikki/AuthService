package com.authService.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.authService.config.SecurityConfigurer;
import com.authService.repository.AuthProfileRepository;
import com.authService.service.AuthProfileService;
import com.authService.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthProfileService authProfileService;
	
	

@Test
void test() {
	fail("Not yet implemented");
}

}
