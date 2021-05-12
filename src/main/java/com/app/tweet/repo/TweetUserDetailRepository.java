package com.app.tweet.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.tweet.model.TweetUserDetail;

public interface TweetUserDetailRepository extends MongoRepository<TweetUserDetail, String> ,PagingAndSortingRepository<TweetUserDetail, String> {

}
