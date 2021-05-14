package com.app.tweet.repo;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.tweet.model.Role;
@EnableScan
@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
  Optional<Role> findByName(String string);
}
