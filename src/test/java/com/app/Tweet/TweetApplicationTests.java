package com.app.Tweet;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.tweet.TweetApplication;

@SpringBootTest
class TweetApplicationTests {

	@InjectMocks
	TweetApplication tweetApplication; 
	@Test
	void contextLoads() {
	}
	@Test
	public void applicationContextTest() {
		tweetApplication.main(new String[] {});
	}
}
