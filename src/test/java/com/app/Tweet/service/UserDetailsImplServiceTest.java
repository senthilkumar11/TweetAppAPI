package com.app.Tweet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import com.app.tweet.model.ERole;
import com.app.tweet.model.Role;
import com.app.tweet.model.User;
import com.app.tweet.repo.UserRepository;
import com.app.tweet.service.impl.UserDetailsImpl;
import com.app.tweet.service.impl.UserDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserDetailsImplServiceTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserDetailsServiceImpl userDetailsService;
	
	User user=new User();
	UserDetailsImpl userDetail;
	Set<Role> roleSet=new HashSet<Role>();
	@BeforeEach
	public void setup() {
		user.setId("id");
		user.setUsername("testUser");
		user.setFirstname("test");
		user.setLastname("user");
		user.setEmail("test@gmail.com");
		user.setPassword("pass1234");
		user.setPhoneNumber("12345678");
		Role role=new Role();
		role.setName(ERole.ROLE_ADMIN);
		role.setId("");
		role.getId();
		role.getName();
		roleSet.add(role);
		user.setRoles(roleSet);	
		userDetail= new UserDetailsImpl( "test","test user", "test@gmail.com","pass1234",null);
		user.getEmail();
		user.getFirstname();
		user.getId();
		user.getLastname();
		user.getPassword();
		user.getPhoneNumber();
		user.getRoles();
		user.getUsername();
	}
	
	@Test
	public void loadbyUserNameTest() {
	when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.of(user));	
	UserDetails result=userDetailsService.loadUserByUsername("test");
	UserDetails userDetails=userDetail.build(user);
	assertEquals(userDetails,result);
	
	}
	@Test
	public void loadbyUserNameTestWithException() {
	Assertions.assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("test"));	
	}
	
}
