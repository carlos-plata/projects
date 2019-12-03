package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Spring REST controller for working with product entity.
 * 
 * @param <X>
 */
@RestController
@RequestMapping("/products")
public class ProductsController<X> {

	@Autowired
	ProductRepository repository;

	/**
	 * Creates a product.
	 *
	 * 1. Accept product as argument. Use {@link RequestBody} annotation. 2. Save
	 * product.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody Product product) {
		if ((StringUtils.isEmpty(product.getName()) && StringUtils.isEmpty(product.getDescription()))
				|| (StringUtils.isEmpty(product.getName()) || StringUtils.isEmpty(product.getDescription()))) {
			throw new HttpServerErrorException(HttpStatus.NOT_ACCEPTABLE);
		} else {
			repository.save(product);
		}

	}

	/**
	 * Finds a product by id.
	 *
	 * @param id The id of the product.
	 * @return The product if found, or a 404 not found.
	 */
	@RequestMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Product product = repository.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(product);
	}

	/**
	 * Lists all products.
	 *
	 * @return The list of products.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<?> listProducts() {
		return repository.findAll();
	}
}