package com.app.tweet.repo;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.tweet.model.Tweet;
@EnableScanCount
@EnableScan
@Repository
public interface TweetRepository extends PagingAndSortingRepository<Tweet, String>  {

	List<Tweet> findByUserId(String userId , Pageable by);


}
