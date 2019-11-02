package com.example.demo.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("com.example.demo.controllers")
public class CartControllerTest {
	@Autowired
	private CartController cartController;

	@Autowired
	private UserController userController;

	@Before
	public void setup() {
		createUser();
	}

	@Test
	public void addToCartAndRemoveFromCart() {
		ModifyCartRequest cartRequest = new ModifyCartRequest();
		cartRequest.setUsername("test");
		cartRequest.setItemId(1L);
		cartRequest.setQuantity(10);
		Cart cart = cartController.addTocart(cartRequest).getBody();

		assertThat(cart.getItems().size()).isEqualTo(10);
		assertThat(cart.getTotal().doubleValue()).isEqualTo(new BigDecimal(29.90).doubleValue());
		cartRequest.setQuantity(5);
		Cart otherCart = cartController.removeFromcart(cartRequest).getBody();
		assertThat(otherCart.getItems().size()).isEqualTo(5);
		assertThat(cart.getTotal().doubleValue()).isEqualTo(new BigDecimal(14.95).doubleValue());

	}

	public void createUser() {
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername("test");
		request.setPassword("testPassword");
		request.setConfirmPassword("testPassword");
		userController.createUser(request);
	}

}
