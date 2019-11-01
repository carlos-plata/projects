package com.example.demo.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

public class OrderControllerTest {

	private UserController userController;

	private UserRepository userRepo = mock(UserRepository.class);

	private CartRepository cartRepo = mock(CartRepository.class);

	private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

	@Before
	public void setup() {
		userController = new UserController();
		TestUtils.injectObjects(userController, "userRepository", userRepo);
		TestUtils.injectObjects(userController, "cartRepository", cartRepo);
		TestUtils.injectObjects(userController, "bCryptPasswordEncoder", encoder);
	}

	@Test
	public void createUser() {
		when(encoder.encode("testPassword")).thenReturn("thisIsHashed");
		CreateUserRequest request = new CreateUserRequest();
		request.setUsername("test");
		request.setPassword("testPassword");
		request.setConfirmPassword("testPassword");

		ResponseEntity<User> response = userController.createUser(request);

		assertNotNull(response);
		assertEquals(200, response.getStatusCodeValue());

		User user = response.getBody();
		assertNotNull(user);
		assertEquals(0, user.getId());
		assertEquals("test", user.getUsername());
		assertEquals("thisIsHashed", user.getPassword());

	}
}
