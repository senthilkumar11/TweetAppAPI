package com.app.tweet.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.tweet.model.ReplyTweet;
import com.app.tweet.model.User;

public interface ReplyTweetRepository extends MongoRepository<ReplyTweet, String>  {

}
