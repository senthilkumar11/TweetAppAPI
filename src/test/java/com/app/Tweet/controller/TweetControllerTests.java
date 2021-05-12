package com.app.Tweet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.app.tweet.controller.TweetController;
import com.app.tweet.controller.request.model.PageReqData;
import com.app.tweet.model.ReplyTweet;
import com.app.tweet.model.Tweet;
import com.app.tweet.model.TweetUserDetail;
import com.app.tweet.service.impl.TweetServiceImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class TweetControllerTests {
	@Mock
	TweetServiceImpl tweetService;
	
	@InjectMocks
	TweetController tweetController;
	TweetUserDetail tweetUserDetail;
	ReplyTweet reply;
	Tweet tweet;
	Date date=new Date();
	@BeforeEach
	public void setup() {
		tweetUserDetail=new TweetUserDetail();
		tweetUserDetail.setId("id");
		tweetUserDetail.setUsername("testUser");
		tweetUserDetail.setFirstname("test");
		tweetUserDetail.setLastname("user");
		tweetUserDetail.setEmail("test@gmail.com");
		
		tweet=new Tweet();
		tweet.setDate(date);
		tweet.setId("id");
		tweet.setUser(tweetUserDetail);
		List<TweetUserDetail> users=new ArrayList<TweetUserDetail>();
		users.add(tweetUserDetail);
		tweet.setLikes(users);
		reply=new ReplyTweet();
		reply.setDate(date);
		reply.setId("id");
		reply.setTweet("reply");
		reply.setUser(tweetUserDetail);
		
	}
	
	@Test
	public void allUsersTest() {
		List<TweetUserDetail> users=new ArrayList<TweetUserDetail>();
		users.add(tweetUserDetail);
		when(tweetService.findAllUser(ArgumentMatchers.anyObject())).thenReturn(users);
		PageReqData pageData=new PageReqData();
		pageData.setPage(1);
		pageData.setSize(10);
		pageData.getPage();
		pageData.getSize();
		PageReqData page = Mockito.mock(PageReqData.class);
		ResponseEntity<List<TweetUserDetail>> results=tweetController.allUsers(page);
		assertEquals(ResponseEntity.ok(users), results);
	}
	
	@Test 
	public void getAllTweetTest() {
		List<Tweet> tweets=new ArrayList<Tweet>();
		tweets.add(tweet);
		when(tweetService.getAllTweet(ArgumentMatchers.anyObject())).thenReturn(tweets);
		PageReqData page = Mockito.mock(PageReqData.class);
		ResponseEntity<List<Tweet>> results=tweetController.getAllTweet(page);
		assertEquals(ResponseEntity.ok(tweets), results);
	}
	
	
	@Test
	public void getAllTweetOfUserTest() {
		List<Tweet> tweets=new ArrayList<Tweet>();
		tweets.add(tweet);
		when(tweetService.getTweetByUser(ArgumentMatchers.anyString(),ArgumentMatchers.anyObject())).thenReturn(tweets);
		PageReqData page=new PageReqData();
				page.setPage(1);
		page.setSize(10);
		ResponseEntity<List<Tweet>> results=tweetController.getAllTweetOfUser("id",page);
		assertEquals(ResponseEntity.ok(tweets), results);
	}
	
	@Test
	public void getTweetTest() {
		Optional<Tweet> tweetOp=Optional.of(tweet);
		when(tweetService.getTweet(ArgumentMatchers.anyString())).thenReturn(tweetOp);	
		ResponseEntity<Tweet> results=tweetController.getTweet("id");
		assertEquals(ResponseEntity.ok(tweet), results);
	}
	
	@Test
	public void saveTweetTest() {
		when(tweetService.saveTweet(ArgumentMatchers.anyString(),ArgumentMatchers.anyObject())).thenReturn(tweet);	
		ResponseEntity<Tweet> results=tweetController.saveTweet("id", tweet);
		assertEquals(ResponseEntity.ok(tweet), results);
	}
	
	@Test
	public void saveReplyTest() {
		when(tweetService.saveReply(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyObject())).thenReturn(tweet);	
		ResponseEntity<Tweet> results=tweetController.saveReply(reply,"id","id");
		assertEquals(ResponseEntity.ok(tweet), results);
	}
	
	@Test
	public void saveUpdateTest() {
		when(tweetService.updateTweet(ArgumentMatchers.anyString(),ArgumentMatchers.anyString(),ArgumentMatchers.anyObject())).thenReturn(tweet);	
		ResponseEntity<Tweet> results=tweetController.updateTweet(tweet, "id", "id");
		assertEquals(ResponseEntity.ok(tweet), results);
	}
	
	
	@Test
	public void likeTweetTest() {
		when(tweetService.likeTweet(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenReturn(tweet);	
		ResponseEntity<Tweet> results=tweetController.likeTweet("id", "id");
		assertEquals(ResponseEntity.ok(tweet), results);
	}
	
	
	@Test
	public void deleteTweetTest() {
		doNothing().when(tweetService).deleteTweet(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
		ResponseEntity<?> results=tweetController.deleteTweet("id", "id");
		assertEquals(new ResponseEntity<Object>(HttpStatus.OK), results);
	}
	

	
	
	
	
	
	
}
