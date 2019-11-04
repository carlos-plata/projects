package com.example.demo.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemControllerTest {
	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void getItems() {
		List<Item> items = itemRepository.findAll();
		assertThat(items.size()).isEqualTo(2);
	}

	@Test
	public void findItemById() {
		Item found = itemRepository.findById(1L).get();
		assertThat(found.getName()).isEqualTo("Round Widget");
	}

	@Test
	public void findItemByName() {
		List<Item> items = itemRepository.findByName("Square Widget");
		assertThat(items.size()).isEqualTo(1);
	}
}
