package com.app.tweet.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "reply")
public class ReplyTweet {
	@Id
	private String id;
	
	@NotBlank
	@Size(max = 144)
	private String tweet;
		
	private Date date;
	
	@DBRef
	private TweetUserDetail user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TweetUserDetail getUser() {
		return user;
	}

	public void setUser(TweetUserDetail user) {
		this.user = user;
	}
}
