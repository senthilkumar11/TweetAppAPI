package com.app.tweet.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.tweet.controller.request.model.PageReqData;
import com.app.tweet.model.ReplyTweet;
import com.app.tweet.model.Tweet;
import com.app.tweet.model.TweetUserDetail;
import com.app.tweet.model.User;
import com.app.tweet.repo.ReplyTweetRepository;
import com.app.tweet.repo.TweetRepository;
import com.app.tweet.repo.TweetUserDetailRepository;
import com.app.tweet.repo.UserRepository;
import com.app.tweet.service.TweetService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TweetRepository tweetRepo;

	@Autowired
	TweetUserDetailRepository tweetUserDetailRepo;

	@Autowired
	ReplyTweetRepository replyRepo;

	@Override
	@Transactional
	public List<TweetUserDetail> findAllUser(PageReqData page) {
		Pageable page1 = PageRequest.of(page.getPage(), page.getSize());
		Page<TweetUserDetail> results = tweetUserDetailRepo.findAll(page1);
		return results.toList();
	}

	@Override
	@Transactional
	public Tweet saveTweet(String userId, Tweet tweet) {
		Optional<TweetUserDetail> user = tweetUserDetailRepo.findById(userId);
		List<ReplyTweet> reply = new ArrayList<ReplyTweet>();
		List<TweetUserDetail> likes = new ArrayList<TweetUserDetail>();
		tweet.setReply(reply);
		tweet.setLikes(likes);
		tweet.setUser(user.get());
		tweet.setDate(new Date());
		return tweetRepo.save(tweet);
	}

	public List<Tweet> getAllTweet(PageReqData page) {
		Pageable page1 = PageRequest.of(page.getPage(), page.getSize(),Sort.by(Sort.Direction.DESC,"Date"));
		Page<Tweet> results = tweetRepo.findAll(page1);
		return results.toList();
	}

	@Override
	public Optional<Tweet> getTweet(String id) {

		return tweetRepo.findById(id);
	}

	@Override
	public List<Tweet> getTweetByUser(String userId, PageReqData page) {
		Pageable page1 = PageRequest.of(page.getPage(), page.getSize(),Sort.by(Sort.Direction.DESC,"Date"));
		return tweetRepo.findByUser(userId,page1);
	}

	@Override
	public Tweet saveReply(String userId, String tweetId, ReplyTweet reply) {

		Optional<TweetUserDetail> user = tweetUserDetailRepo.findById(userId);
		reply.setUser(user.get());
		Optional<Tweet> result = tweetRepo.findById(tweetId.trim());
		Tweet tweet = result.get();
		reply.setDate(new Date());
		reply = replyRepo.save(reply);
		List<ReplyTweet> replyList = tweet.getReply();
		replyList.add(reply);
		tweet.setReply(replyList);

		return tweetRepo.save(tweet);
	}

	@Override
	public Tweet updateTweet(String userId, String tweetId, Tweet updatedTweet) {
		Optional<Tweet> optTweet=tweetRepo.findById(tweetId);
		Tweet tweet=optTweet.get();
		tweet.setTweet(updatedTweet.getTweet());
		return tweetRepo.save(tweet);
	}

	@Override
	public void deleteTweet(String userId, String tweetId) {
		tweetRepo.deleteById(tweetId);		
	}

	@Override
	public Tweet likeTweet(String userId, String tweetId) {
		Optional<TweetUserDetail> user = tweetUserDetailRepo.findById(userId);
		Optional<Tweet> optTweet=tweetRepo.findById(tweetId);
		List<TweetUserDetail> likes = optTweet.get().getLikes();
		likes.add(user.get());
		Tweet tweet=optTweet.get();
		tweet.setLikes(likes);
		return tweetRepo.save(tweet);
	}
}
