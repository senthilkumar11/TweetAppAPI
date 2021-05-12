package com.app.tweet.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.tweet.controller.request.model.PageReqData;
import com.app.tweet.model.ReplyTweet;
import com.app.tweet.model.Tweet;
import com.app.tweet.model.TweetUserDetail;
import com.app.tweet.service.impl.TweetServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweet")
public class TweetController {
	@Autowired
	TweetServiceImpl tweetService = new TweetServiceImpl();

	@GetMapping("/users/all")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<TweetUserDetail>> allUsers(@PathParam(value = "params") PageReqData page) {
		List<TweetUserDetail> users = tweetService.findAllUser(page);
		return new ResponseEntity<List<TweetUserDetail>>(users, HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Tweet>> getAllTweet(@PathParam(value = "params") PageReqData page) {
		return ResponseEntity.ok(tweetService.getAllTweet(page));
	}

	@GetMapping("{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Tweet> getTweet(@PathVariable("id") String id) {
		Optional<Tweet> tweet = tweetService.getTweet(id);
		return ResponseEntity.ok(tweet.get());
	
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Tweet>> getAllTweetOfUser(@PathVariable("id") String userId,@PathParam(value = "params") PageReqData page) {
		List<Tweet> tweet = tweetService.getTweetByUser(userId,page);
		return ResponseEntity.ok(tweet);
		

	}

	@PostMapping("{userId}/add")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Tweet> saveTweet(@PathVariable("userId") String userId, @RequestBody Tweet tweet) {
		System.out.println(tweet.getDate());
		Tweet response = tweetService.saveTweet(userId, tweet);
		return ResponseEntity.ok(response);
	}

	@PostMapping("{userId}/reply/{tweetId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Tweet> saveReply(@RequestBody ReplyTweet reply, @PathVariable("userId") String userId,
			@PathVariable("tweetId") String tweetId) {
		Tweet response = tweetService.saveReply(userId, tweetId, reply);
		return ResponseEntity.ok(response);
	}

	@PutMapping("{userId}/update/{tweetId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Tweet> updateTweet(@RequestBody Tweet tweet, @PathVariable("userId") String userId,
			@PathVariable("tweetId") String tweetId) {
		Tweet response = tweetService.updateTweet(userId, tweetId, tweet);
		return ResponseEntity.ok(response);
		
	}

	@PutMapping("{userId}/like/{tweetId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Tweet> likeTweet( @PathVariable("userId") String userId,
			@PathVariable("tweetId") String tweetId) {
		Tweet response = tweetService.likeTweet(userId, tweetId);
		return ResponseEntity.ok(response);
		
	}

	@DeleteMapping("{userId}/delete/{tweetId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteTweet(@PathVariable("userId") String userId,
			@PathVariable("tweetId") String tweetId) {
		tweetService.deleteTweet(userId, tweetId);
		return new ResponseEntity< Object>(HttpStatus.OK);  

	}

}
