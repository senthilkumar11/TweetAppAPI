package com.app.tweet.service;


import java.util.List;
import java.util.Optional;

import com.app.tweet.controller.request.model.PageReqData;
import com.app.tweet.model.ReplyTweet;
import com.app.tweet.model.Tweet;
import com.app.tweet.model.TweetUserDetail;

public interface TweetService {
	List<TweetUserDetail> findAllUser(PageReqData page);
	Tweet saveTweet(String userId,Tweet tweet);
	public List<Tweet> getAllTweet(PageReqData page);
	Optional<Tweet> getTweet(String id);
	List<Tweet> getTweetByUser(String userId,PageReqData page);
	Tweet saveReply(String userId,String tweetId,ReplyTweet reply);
	Tweet updateTweet(String userId,String tweetId,Tweet tweet);
	void deleteTweet(String userId,String tweetId);
	Tweet likeTweet(String userId,String tweetId);
}
