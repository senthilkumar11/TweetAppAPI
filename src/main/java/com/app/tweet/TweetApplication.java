package com.app.tweet;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.tweet.model.*;
import com.app.tweet.repo.RoleRepository;

@SpringBootApplication
public class TweetApplication {
	
	@Autowired
	RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return ()->{
			Optional<Role> admin= roleRepository.findByName(ERole.ROLE_ADMIN);
			if(!admin.isPresent()) {
				roleRepository.save(new Role(ERole.ROLE_ADMIN));
			}
			Optional<Role> user= roleRepository.findByName(ERole.ROLE_USER);
			if(!user.isPresent()) {
				roleRepository.save(new Role(ERole.ROLE_USER));
			}
			System.out.println("Role save completed");
		};
	}
	
}
