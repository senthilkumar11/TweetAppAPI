package com.app.tweet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.app.tweet.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Producer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public String postToTopic(User user) {
		log.info(String.format("User registered producer end -> %s", user));
		this.kafkaTemplate.send("users", user);
        return "User Registration Successfull";
	}

}
