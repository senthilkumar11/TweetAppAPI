package com.app.tweet.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class RoleConverter implements DynamoDBTypeConverter<String, Set<Role>> {

	@Override
	public String convert(Set<Role> object) {
	
	return new Gson().toJson(object);
	}

	@Override
	public Set<Role> unconvert(String object) {
		try {		
		List<Role> roleList= Arrays.asList(new Gson().fromJson(object, Role[].class));
		Set<Role> roleSet=new HashSet<Role>();
		roleSet.addAll(roleList);
		return roleSet;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Set<Role> roleSet=new HashSet<Role>();
		roleSet.add(new Role("ROLE_ADMIN"));
		return roleSet;
	}

}
