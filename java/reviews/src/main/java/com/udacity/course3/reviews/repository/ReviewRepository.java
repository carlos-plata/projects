package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.udacity.course3.reviews.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	@Query("select new Review(r.reviewId, r.title, r.description, r.productId) from Review r where r.productId=:productId")
	List<Review> getReviewsByProductId(Integer productId);
}
