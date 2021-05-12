package com.app.Tweet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.app.tweet.model.ERole;
import com.app.tweet.model.Role;
import com.app.tweet.model.User;
import com.app.tweet.repo.UserRepository;
import com.app.tweet.service.impl.UserDetailsImpl;
import com.app.tweet.service.impl.UserDetailsServiceImpl;



@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserDetailsImplTests {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserDetailsImpl userDetailsImpl;
	
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
		userDetail.getEmail();
		userDetail.getId();
		userDetail.getPassword();
		userDetail.getUsername();
		
		userDetail.isAccountNonExpired();
		userDetail.isAccountNonLocked();
		userDetail.isCredentialsNonExpired();
		userDetail.isEnabled();
		userDetail.getAuthorities();
	
	}
	@Test
	public void equalsTestWithSameObj()
	{		
		assertEquals(true, userDetailsImpl.equals(userDetailsImpl));
	}
	@Test
	public void equalsTestWithNull()
	{		
		assertEquals(false, userDetailsImpl.equals(null));

		assertEquals(false, userDetailsImpl.equals(user));
	}
	
}
