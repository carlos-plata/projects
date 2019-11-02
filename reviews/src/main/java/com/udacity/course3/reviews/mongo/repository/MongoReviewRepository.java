package com.udacity.course3.reviews.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.mongo.entity.MongoReview;

public interface MongoReviewRepository extends MongoRepository<MongoReview, String> {
	List<MongoReview> getReviewsByProductId(String productId);
}