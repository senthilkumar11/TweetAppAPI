package com.app.tweet.repo;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.tweet.model.ReplyTweet;
@EnableScan
@Repository
public interface ReplyTweetRepository extends PagingAndSortingRepository<ReplyTweet, String> {

}
