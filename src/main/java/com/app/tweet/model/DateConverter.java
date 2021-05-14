
package com.app.tweet.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;

public class DateConverter implements DynamoDBTypeConverter<String, Date> {

	@Override
	public String convert(Date object) {
	return new Gson().toJson(object);
	}

	@Override
	public Date unconvert(String object) {
		try {		
		Date date= new Gson().fromJson(object, Date.class);
		return date;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}