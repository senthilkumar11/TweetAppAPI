package com.app.tweet.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Tweet {

	@Id
	private String id;

	@NotBlank
	@Size(max = 144)
	private String tweet;

	private Date date;

	@DBRef
	private TweetUserDetail user;

	@DBRef
	private List<ReplyTweet> reply;
	
	@DBRef
	private List<TweetUserDetail> likes;

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

	public List<ReplyTweet> getReply() {
		return reply;
	}

	public void setReply(List<ReplyTweet> reply) {
		this.reply = reply;
	}

	public List<TweetUserDetail> getLikes() {
		return likes;
	}

	public void setLikes(List<TweetUserDetail> likes) {
		this.likes = likes;
	}


}
