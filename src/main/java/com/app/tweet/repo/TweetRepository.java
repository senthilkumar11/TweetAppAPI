package com.app.tweet.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.app.tweet.model.Tweet;

public interface TweetRepository extends MongoRepository<Tweet, String>  {
	@Query("{'user.id':?0}")
	List<Tweet> findByUser(String userId , Pageable by);


}
