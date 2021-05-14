package com.app.tweet.repo;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.tweet.model.TweetUserDetail;
@EnableScanCount
@EnableScan
@Repository
public interface TweetUserDetailRepository extends PagingAndSortingRepository<TweetUserDetail, String> {

}
