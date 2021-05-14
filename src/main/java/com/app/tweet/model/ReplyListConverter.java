package com.app.tweet.model;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;

public class ReplyListConverter implements DynamoDBTypeConverter<String, List<ReplyTweet>> {

	@Override
	public String convert(List<ReplyTweet> object) {
	return new Gson().toJson(object);
	}

	@Override
	public List<ReplyTweet> unconvert(String object) {
		try {		
		List<ReplyTweet> ReplyTweetList= Arrays.asList(new Gson().fromJson(object, ReplyTweet[].class));
		return ReplyTweetList;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
