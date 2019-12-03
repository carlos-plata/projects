package com.udacity.pricing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.domain.price.PriceRepository;

/**
 * Implements a REST-based controller for the pricing service.
 */
@RestController
@RequestMapping("/services/price")
public class PricingController {

	@Autowired
	PriceRepository repository;

	/**
	 * Gets the price for a requested vehicle.
	 * 
	 * @param vehicleId ID number of the vehicle for which the price is requested
	 * @return price of the vehicle, or error that it was not found.
	 */
	@GetMapping
	public Price get(@RequestParam Long vehicleId) {
		return repository.findById(vehicleId).get();
	}
}
