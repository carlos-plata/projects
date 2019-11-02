package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.udacity.course3.reviews.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query("select new Comment(c.commentId, c.title, c.description, c.reviewId) from Comment c where c.reviewId=:reviewId")
	List<Comment> getCommentsByReviewId(Integer reviewId);

}
