package com.app.tweet.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;

public class UserDetailConverter implements DynamoDBTypeConverter<String, TweetUserDetail> {

	@Override
	public String convert(TweetUserDetail object) {
	return new Gson().toJson(object);
	}

	@Override
	public TweetUserDetail unconvert(String object) {
		try {		
		TweetUserDetail TweetUserDetail= new Gson().fromJson(object, TweetUserDetail.class);
		return TweetUserDetail;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}