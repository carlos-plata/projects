package com.udacity.course3.reviews.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.udacity.course3.reviews.mongo.entity.MongoReview;
import com.udacity.course3.reviews.mongo.repository.MongoReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	MongoReviewRepository repository;

	/**
	 * Find review by Id from MongoDB.
	 *
	 * @param reviewId The id of the review.
	 * @return The MongoReview object.
	 */
	@Override
	public Optional<MongoReview> findById(String reviewId) {

		return repository.findById(reviewId);
	}

	/**
	 * Lists reviews by product from MongoDB.
	 *
	 * @param productId The id of the product.
	 * @return The list of reviews.
	 */
	@Override
	public List<MongoReview> getReviewsByProductId(String productId) {
		return repository.getReviewsByProductId(productId);
	}

	/**
	 * Creates a review in MongoDB for a product.
	 * <p>
	 * 1. Add argument for review entity. Use {@link RequestBody} annotation. 2.
	 * Check for existence of product. 3. If product not found, return NOT_FOUND. 4.
	 * If found, save review.
	 *
	 * @param productId The id of the product.
	 * @return The created review or 404 if product id is not found.
	 */
	@Override
	public MongoReview save(MongoReview review) {
		return repository.save(review);
	}

	/**
	 * Lists reviews by product from MongoDB.
	 *
	 * @param The list of reviewIds.
	 * @return The list of reviews.
	 */
	@Override
	public Iterable<MongoReview> findAllReviewsByIds(List<String> reviewIds) {

		return repository.findAllById(reviewIds);
	}
}
