package com.udacity.course3.reviews.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Integer reviewId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "product_id")
	private Integer productId;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
	private Product product;

	@JsonIgnore
	@OneToMany(mappedBy = "review")
	private List<Comment> comments;

	public Review() {
	}

	public Review(Integer reviewId, String title, String description, Integer productId) {
		this.reviewId = reviewId;
		this.title = title;
		this.description = description;
		this.productId = productId;
	}

	/**
	 * @return the reviewId
	 */
	public Integer getReviewId() {
		return reviewId;
	}

	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
