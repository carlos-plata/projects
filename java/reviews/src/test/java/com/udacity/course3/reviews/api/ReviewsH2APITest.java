package com.udacity.course3.reviews.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongo.entity.MongoReview;
import com.udacity.course3.reviews.mongo.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

/**
 * Implements testing of the Reviews API using H2 database
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewsH2APITest {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private CommentRepository commentRepo;

	@Test
	public void createAndFindProduct() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		Optional<Product> foundProduct = productRepo.findById(product.getProductId());
		assertNotNull(foundProduct);
		assertEquals(product.getName(), foundProduct.get().getName());
	}

	@Test
	public void createAndFindReview() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		Review review = reviewRepo.save(new Review(null, "OK, cool.", "this is a cool review", product.getProductId()));
		Optional<Review> foundReview = reviewRepo.findById(review.getReviewId());
		assertNotNull(foundReview);
		assertEquals(review.getReviewId(), foundReview.get().getReviewId());
	}

	@Test
	public void createAndFindComment() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		Review review = reviewRepo.save(new Review(null, "OK, cool.", "this is a cool review", product.getProductId()));
		Comment comment = commentRepo
				.save(new Comment(null, "wow, you rock", "just want to say YAY!", review.getReviewId()));
		Optional<Comment> foundComment = commentRepo.findById(comment.getCommentId());
		assertNotNull(foundComment);
		assertEquals(comment.getCommentId(), foundComment.get().getCommentId());
	}

	@Test
	public void listProducts() {
		Product productOne = productRepo.save(new Product("phone", "an elegant mobile phone."));
		Product productTwo = productRepo.save(new Product("phone", "a cool satellite phone."));
		List<Product> products = productRepo.findAll();
		assertNotNull(products);
		assertEquals(products.size(), 2);
	}

	@Test
	public void listReviews() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		Review reviewOne = reviewRepo
				.save(new Review(null, "OK, cool.", "this is a cool review", product.getProductId()));
		Review reviewTwo = reviewRepo
				.save(new Review(null, "OK, nice.", "this is a nice review", product.getProductId()));
		List<Review> reviews = reviewRepo.findAll();
		assertNotNull(reviews);
		assertEquals(reviews.size(), 2);
	}

	@Test
	public void listComments() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		Review review = reviewRepo.save(new Review(null, "OK, cool.", "this is a cool review", product.getProductId()));
		Comment commentOne = commentRepo
				.save(new Comment(null, "wow, you rock", "just want to say YAY!", review.getReviewId()));
		Comment commentTwo = commentRepo.save(
				new Comment(null, "wow, you are a star", "just want to say nice work man!", review.getReviewId()));
		List<Comment> comments = commentRepo.findAll();
		assertNotNull(comments);
		assertEquals(comments.size(), 2);
	}

}
