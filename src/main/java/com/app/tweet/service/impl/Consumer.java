package com.app.tweet.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.app.tweet.model.User;

import lombok.extern.slf4j.Slf4j;

 

@Slf4j
@Service
public class Consumer {
    
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    
    @KafkaListener(topics = "users", groupId = "group_id")
    public String consume(User user) {
    	log.info(String.format("User registered producer end -> %s", user));
        this.kafkaTemplate.send("users", user);
        return "User Registration Successfull";
    }
}
 
