package com.udacity.course3.reviews.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.mongo.entity.MongoComment;
import com.udacity.course3.reviews.mongo.entity.MongoReview;
import com.udacity.course3.reviews.mongo.service.ReviewService;
import com.udacity.course3.reviews.repository.ProductRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MongoReviewTest {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private ReviewService service;

	@Test
	public void createMongoReview() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		MongoReview review = service
				.save(new MongoReview(null, "OK, cool.", "this is a cool review", product.getProductId().toString()));
		Optional<MongoReview> foundReview = service.findById(review.getId());
		assertNotNull(foundReview);
		assertEquals(review.getId(), foundReview.get().getId());
	}

	@Test
	public void createMongoComment() {
		Product product = productRepo.save(new Product("phone", "an elegant mobile phone."));
		MongoReview review = service
				.save(new MongoReview(null, "OK, cool.", "this is a cool review", product.getProductId().toString()));
		review.getComments().add(new MongoComment("wow, you rock", "just want to say YAY!"));
		MongoReview savedReview = service.save(review);
		assertEquals(savedReview.getComments().size(), review.getComments().size());
	}

}
