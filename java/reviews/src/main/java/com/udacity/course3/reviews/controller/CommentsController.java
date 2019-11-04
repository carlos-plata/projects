package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongo.entity.MongoComment;
import com.udacity.course3.reviews.mongo.entity.MongoReview;
import com.udacity.course3.reviews.mongo.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	MongoReviewRepository mongoReviewRepo;
	@Autowired
	ReviewRepository reviewRepository;
	@Autowired
	CommentRepository repository;

	/**
	 * Creates a comment for a review.
	 *
	 * 1. Add argument for comment entity. Use {@link RequestBody} annotation. 2.
	 * Check for existence of review. 3. If review not found, return NOT_FOUND. 4.
	 * If found, save comment.
	 *
	 * @param reviewId The id of the review.
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
	public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
			@RequestBody Comment comment) {
		if ((StringUtils.isEmpty(comment.getTitle()) && StringUtils.isEmpty(comment.getDescription()))
				|| (StringUtils.isEmpty(comment.getTitle()) || StringUtils.isEmpty(comment.getDescription()))) {
			throw new HttpServerErrorException(HttpStatus.NOT_ACCEPTABLE);
		} else {
			if (mongoReviewRepo.findById(reviewId.toString()).isPresent()) {
				MongoReview review = mongoReviewRepo.findById(reviewId.toString()).get();
				review.getComments().add(new MongoComment(comment.getTitle(), comment.getDescription()));
				return ResponseEntity.ok(mongoReviewRepo.save(review));
			} else {
				throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
			}
		}
	}

	/**
	 * List comments for a review.
	 *
	 * 2. Check for existence of review. 3. If review not found, return NOT_FOUND.
	 * 4. If found, return list of comments.
	 *
	 * @param reviewId The id of the review.
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
	public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
		if (mongoReviewRepo.findById(reviewId.toString()).isPresent()
				&& (mongoReviewRepo.findById(reviewId.toString()).get().getComments().size() > 0)) {
			return mongoReviewRepo.findById(reviewId.toString()).get().getComments();
		} else {
			throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
		}
	}
}