
package com.app.tweet.model;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;

public class UserDetailListConverter implements DynamoDBTypeConverter<String, List<TweetUserDetail>> {

	@Override
	public String convert(List<TweetUserDetail> object) {
	return new Gson().toJson(object);
	}

	@Override
	public List<TweetUserDetail> unconvert(String object) {
		try {		
		List<TweetUserDetail> TweetUserDetailList= Arrays.asList(new Gson().fromJson(object, TweetUserDetail[].class));
		return TweetUserDetailList;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
