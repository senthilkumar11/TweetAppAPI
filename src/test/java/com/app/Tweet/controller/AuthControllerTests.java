package com.app.Tweet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.app.tweet.controller.AuthController;
import com.app.tweet.controller.request.model.LoginRequest;
import com.app.tweet.controller.request.model.SignupRequest;
import com.app.tweet.controller.response.model.JwtResponse;
import com.app.tweet.controller.response.model.MessageResponse;
import com.app.tweet.model.ERole;
import com.app.tweet.model.Role;
import com.app.tweet.model.User;
import com.app.tweet.repo.RoleRepository;
import com.app.tweet.repo.UserRepository;
import com.app.tweet.security.jwt.JwtUtils;
import com.app.tweet.service.impl.UserDetailsImpl;
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AuthControllerTests {
	@Mock
	AuthenticationManager authenticationManager;

	@Mock
	UserRepository userRepository;

	@Mock
	RoleRepository roleRepository;

	@Mock
	PasswordEncoder encoder;
	@Mock
	SignupRequest signupReq;

	@Mock
	JwtUtils jwtUtils;

	@InjectMocks
	AuthController authController;
	
	LoginRequest loginRequest  = new LoginRequest();;
	UserDetailsImpl userDetails;
	JwtResponse jwt;
	List<String> roles=new ArrayList<String>();
	SignupRequest signupRequest;
	Set<String> roleSet=new HashSet<String>();
	@BeforeEach
	public void setup() {
		
		loginRequest.setPassword("test");
		loginRequest.setUsername("test user");
		
		
		
		roles.add("ROLE_USER");
		jwt=new JwtResponse("testJWT", "test","test user", "test@gmail.com",roles);
		jwt.getAccessToken();
		jwt.getEmail();
		jwt.getId();
		jwt.getRoles();
		jwt.getTokenType();
		jwt.getUsername();
		jwt.setAccessToken("testJWT");
		jwt.setEmail("test@gmail.com");
		jwt.setId("test");
		jwt.setTokenType("Barrier");
		jwt.setUsername("test user");
		signupRequest=new SignupRequest();
		signupRequest.setEmail("test@gmail.com");
		signupRequest.setFirstname("test");
		signupRequest.setLastname("user");
		signupRequest.setPassword("test1234");
		signupRequest.setUsername("test12");
		signupRequest.setPhoneNumber("12345678");
		
		roleSet.add("admin");
		roleSet.add("mod");
		signupRequest.setRole(roleSet);
	}
	
	@Test
	public void authenticateUserTestWithBadRequest() {
		 assertThrows(NullPointerException.class, ()->{authController.authenticateUser(loginRequest).getStatusCode();});
			
	}
//	@Test
//	public void authenticateUserTest() {
//		
//		when(authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))).thenReturn(authentication);
//
//		when(jwtUtils.generateJwtToken(ArgumentMatchers.anyObject())).thenReturn("testJWT");
//		
//		when(authentication.getPrincipal()).thenReturn(userDetails);
//		when(userDetails.getAuthorities().stream().map(item -> item.getAuthority())
//				.collect(Collectors.toList())).thenReturn(roles);
//		
//		ResponseEntity<?> results=authController.authenticateUser(loginRequest);
//		assertEquals(ResponseEntity.ok(results), results);
//
//	}
    @Test
    public void registerUserTest() {
    	when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
    	when(userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(false);
    	Role userRole=new Role(ERole.ROLE_USER);
    	when(roleRepository.findByName(ArgumentMatchers.anyObject())).thenReturn(Optional.of(userRole));
    	when(userRepository.save(ArgumentMatchers.anyObject())).thenReturn(new User());
    	MessageResponse msgRes=new MessageResponse("User registered successfully!");
    	ResponseEntity<MessageResponse> results=(ResponseEntity<MessageResponse>) authController.registerUser(signupRequest);
    	assertEquals("User registered successfully!", results.getBody().getMessage());
    }
    @Test
    public void registerUserTestWithExistingUser() {
    	when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(true);
    	ResponseEntity<MessageResponse> results=(ResponseEntity<MessageResponse>) authController.registerUser(signupRequest);
    	assertEquals("Error: Username is already taken!", results.getBody().getMessage());
    }
    @Test
    public void registerUserTestWithExistingEmail() {
    	when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
    	when(userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(true);
    	ResponseEntity<MessageResponse> results=(ResponseEntity<MessageResponse>) authController.registerUser(signupRequest);
    	assertEquals("Error: Email is already in use!", results.getBody().getMessage());
    }
    @Test
    public void registerUserForRoleTest() {
    	when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
    	when(userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(false);
    	Role userRole=new Role();
    	when(roleRepository.findByName(ArgumentMatchers.anyObject())).thenReturn(Optional.of(userRole));
    	when(userRepository.save(ArgumentMatchers.anyObject())).thenReturn(new User());
    	MessageResponse msgRes=new MessageResponse("User registered successfully!");
    	ResponseEntity<MessageResponse> results=(ResponseEntity<MessageResponse>) authController.registerUser(signupRequest);
    	assertEquals("User registered successfully!", results.getBody().getMessage());
    }
    @Test
    public void registerUserForRoleNullTest() {
    	when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
    	when(userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(false);
    	Role userRole=new Role(ERole.ROLE_USER);
    	when(roleRepository.findByName(ArgumentMatchers.anyObject())).thenReturn(Optional.of(userRole));
    	when(userRepository.save(ArgumentMatchers.anyObject())).thenReturn(new User());
    	MessageResponse msgRes=new MessageResponse("User registered successfully!");
    	msgRes.setMessage("User registered successfully!");
    	signupRequest.setRole(null);
    	ResponseEntity<MessageResponse> results=(ResponseEntity<MessageResponse>) authController.registerUser(signupRequest);
    	assertEquals("User registered successfully!", results.getBody().getMessage());
    }
}
