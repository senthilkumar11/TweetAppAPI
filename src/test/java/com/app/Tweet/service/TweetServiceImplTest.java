package com.app.Tweet.service;

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
import org.springframework.test.context.ActiveProfiles;

import com.app.tweet.controller.request.model.PageReqData;
import com.app.tweet.model.ReplyTweet;
import com.app.tweet.model.Tweet;
import com.app.tweet.model.TweetUserDetail;
import com.app.tweet.repo.ReplyTweetRepository;
import com.app.tweet.repo.TweetRepository;
import com.app.tweet.repo.TweetUserDetailRepository;
import com.app.tweet.repo.UserRepository;
import com.app.tweet.service.impl.TweetServiceImpl;
import com.app.tweet.service.impl.UserDetailsImpl;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class TweetServiceImplTest {
	@InjectMocks
	TweetServiceImpl tweetService;
	@Mock
	UserRepository userRepository;

	@Mock
	TweetRepository tweetRepo;

	@Mock
	TweetUserDetailRepository tweetUserDetailRepo;

	@Mock
	ReplyTweetRepository replyRepo;

	TweetUserDetail tweetUserDetail = new TweetUserDetail();
	ReplyTweet reply = new ReplyTweet();
	Tweet tweet = new Tweet();
	Date date = new Date();

	@BeforeEach
	public void setup() {

		tweetUserDetail.setId("id");
		tweetUserDetail.setUsername("testUser");
		tweetUserDetail.setFirstname("test");
		tweetUserDetail.setLastname("user");
		tweetUserDetail.setEmail("test@gmail.com");
		tweetUserDetail.getEmail();
		tweetUserDetail.getFirstname();
		tweetUserDetail.getId();
		tweetUserDetail.getLastname();
		tweetUserDetail.getUsername();
		tweet.setDate(date);
		tweet.setId("id");
		tweet.setUser(tweetUserDetail);
		tweet.setTweet("testing tweet");
		List<ReplyTweet> emptyReplyList = new ArrayList<ReplyTweet>();
		tweet.setReply(emptyReplyList);
		List<TweetUserDetail> emptyLikes = new ArrayList<TweetUserDetail>();
		tweet.setLikes(emptyLikes);
		List<TweetUserDetail> users = new ArrayList<TweetUserDetail>();
		users.add(tweetUserDetail);
		tweet.setLikes(users);
		tweet.getDate();
		tweet.getId();
		tweet.getLikes();
		tweet.getReply();
		tweet.getTweet();
		tweet.getUser();
		reply.setDate(date);
		reply.setId("id");
		reply.setTweet("reply");
		reply.setUser(tweetUserDetail);
		reply.getDate();
		reply.getId();
		reply.getTweet();
		reply.getUser();
	}

//	@Test
//	public void findAllUsersTest() {
//		List<TweetUserDetail> users=new ArrayList<TweetUserDetail>();
//		users.add(tweetUserDetail);	
//		when(tweetService.findAllUsers(ArgumentMatchers.anyObject())).thenReturn(users);
//		PageReqData page = new PageReqData();
//		page.setPage(1);
//		page.setSize(5);
//		List<TweetUserDetail> results=tweetService.findAllUser(page);
//		assertEquals(users, results);
//	}

//	@Test
//	public void getAllTweetTest() {
//		List<Tweet> tweets=new ArrayList<Tweet>();
//		tweets.add(tweet);
//		when(tweetService.getAllTweet(ArgumentMatchers.anyObject())).thenReturn(tweets);
//		PageReqData page = Mockito.mock(PageReqData.class);
//		ResponseEntity<List<Tweet>> results=tweetController.getAllTweet(page);
//		assertEquals(ResponseEntity.ok(tweets), results);
//	}
//	
//	
//	@Test
//	public void getAllTweetOfUserTest() {
//		List<Tweet> tweets=new ArrayList<Tweet>();
//		tweets.add(tweet);
//		when(tweetService.getTweetByUser(ArgumentMatchers.anyString())).thenReturn(tweets);	
//		ResponseEntity<List<Tweet>> results=tweetController.getAllTweetOfUser("id");
//		assertEquals(ResponseEntity.ok(tweets), results);
//	}
//	
	@Test
	public void getTweetTest() {
		Optional<Tweet> tweetOp = Optional.of(tweet);
		when(tweetRepo.findById(ArgumentMatchers.anyString())).thenReturn(tweetOp);
		Optional<Tweet> results = tweetService.getTweet("id");
		assertEquals(Optional.of(tweet), results);
	}

	@Test
	public void saveTweetTest() {
		when(tweetRepo.save(ArgumentMatchers.anyObject())).thenReturn(tweet);
		when(tweetUserDetailRepo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(tweetUserDetail));
		Tweet results = tweetService.saveTweet("id", tweet);
		assertEquals(tweet, results);
	}

	@Test
	public void saveReplyTest() {
		when(tweetUserDetailRepo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(tweetUserDetail));
		when(tweetRepo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(tweet));
		when(replyRepo.save(ArgumentMatchers.anyObject())).thenReturn(reply);
		when(tweetRepo.save(ArgumentMatchers.anyObject())).thenReturn(tweet);
		Tweet results = tweetService.saveReply("userId", "tweetId", reply);
		assertEquals(tweet, results);
	}

	@Test
	public void saveUpdateTest() {
		when(tweetRepo.save(ArgumentMatchers.anyObject())).thenReturn(tweet);
		when(tweetRepo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(tweet));
		Tweet results = tweetService.updateTweet("userId", "tweetId", tweet);
		assertEquals(tweet, results);
	}

	@Test
	public void likeTweetTest() {
		when(tweetUserDetailRepo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(tweetUserDetail));
		when(tweetRepo.save(ArgumentMatchers.anyObject())).thenReturn(tweet);
		when(tweetRepo.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(tweet));
		Tweet results = tweetService.likeTweet("userId", "tweetId");
		assertEquals(tweet, results);
	}

	@Test
	public void deleteTweetTest() {
		doNothing().when(tweetRepo).deleteById(ArgumentMatchers.anyString());
		tweetService.deleteTweet("userId", "tweetId");

	}

}
