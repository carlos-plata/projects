package com.udacity.course3.reviews.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.udacity.course3.reviews.mongo.entity.MongoReview;

public interface ReviewService {

	/**
	 * Find review by Id from MongoDB.
	 *
	 * @param reviewId The id of the review.
	 * @return The MongoReview object.
	 */
	Optional<MongoReview> findById(String reviewId);

	/**
	 * Lists reviews by product from MongoDB.
	 *
	 * @param productId The id of the product.
	 * @return The list of reviews.
	 */
	List<MongoReview> getReviewsByProductId(String productId);

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
	MongoReview save(MongoReview review);

	/**
	 * Lists reviews by product from MongoDB.
	 *
	 * @param The list of reviewIds.
	 * @return The list of reviews.
	 */
	Iterable<MongoReview> findAllReviewsByIds(List<String> reviewIds);

}
