package com.app.tweet.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.tweet.model.ERole;
import com.app.tweet.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
