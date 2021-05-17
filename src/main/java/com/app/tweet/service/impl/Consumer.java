//package com.app.tweet.service.impl;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import com.app.tweet.model.User;
//
//import lombok.extern.slf4j.Slf4j;
//
// 
//
//@Slf4j
//@Service
//public class Consumer {
//    
//
//    
//    @KafkaListener(topics = "users", groupId = "group_id")
//    public void consume(User user) {
//    	log.info(String.format("User registered producer end -> %s", user));
//        
//    }
//}
// 
