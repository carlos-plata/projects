package com.udacity.course3.reviews.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongo.entity.MongoReview;
import com.udacity.course3.reviews.mongo.service.ReviewService;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

	@Autowired
	ReviewRepository repository;
	@Autowired
	ReviewService service;
	@Autowired
	ProductRepository productRepository;

	/**
	 * Creates a review for a product.
	 * <p>
	 * 1. Add argument for review entity. Use {@link RequestBody} annotation. 2.
	 * Check for existence of product. 3. If product not found, return NOT_FOUND. 4.
	 * If found, save review.
	 *
	 * @param productId The id of the product.
	 * @return The created review or 404 if product id is not found.
	 */
	@RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
	public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
			@RequestBody Review review) {
		if ((StringUtils.isEmpty(review.getTitle()) && StringUtils.isEmpty(review.getDescription()))
				|| (StringUtils.isEmpty(review.getTitle()) || StringUtils.isEmpty(review.getDescription()))) {
			throw new HttpServerErrorException(HttpStatus.NOT_ACCEPTABLE);
		} else {
			if (productRepository.findById(productId).isPresent()) {
				review.setProductId(productId);
				Review savedReview = repository.save(review);
				return ResponseEntity.ok(service.save(new MongoReview(savedReview.getReviewId().toString(),
						savedReview.getTitle(), savedReview.getDescription(), savedReview.getProductId().toString())));
			} else {
				throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
			}
		}

	}

	/**
	 * Lists reviews by product.
	 *
	 * @param productId The id of the product.
	 * @return The list of reviews.
	 */
	@RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
		if (productRepository.findById(productId).isPresent()) {
			Review review = new Review();
			review.setProductId(productId);
			org.springframework.data.domain.Example<Review> reviewExample = org.springframework.data.domain.Example
					.of(review);
			Iterable<Review> reviews = repository.findAll(reviewExample);
			List<String> reviewIds = new ArrayList<String>();
			for (Review item : reviews) {
				reviewIds.add(item.getReviewId().toString());
			}
			List<MongoReview> mongoReviews = new ArrayList<MongoReview>();
			Iterator<MongoReview> iterator = service.findAllReviewsByIds(reviewIds).iterator();
			while (iterator.hasNext()) {
				mongoReviews.add(iterator.next());

			}
			return new ResponseEntity<List<?>>(mongoReviews, HttpStatus.OK);
		} else {
			throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
		}
	}
}