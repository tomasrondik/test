package com.adastragrp.test;

import com.adastragrp.test.domain.StoreCategory;
import com.adastragrp.test.dto.CreateStore;
import com.adastragrp.test.dto.Store;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getStore_Happy_Success() throws Exception {

		final String createdStoreJson = mockMvc.perform(MockMvcRequestBuilders.get("/store/uuid1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();

		final Store createdStore = objectMapper.readValue(createdStoreJson, Store.class);
		assertEquals("uuid1", createdStore.getId());
		assertEquals("Starbucks", createdStore.getName());
		assertEquals("+100100200", createdStore.getPhoneNumber());
		assertEquals(StoreCategory.COFFEE_HOUSE, createdStore.getCategory());
		assertEquals("hello@starbucks.com", createdStore.getEmailAddress());
	}

	@Test
	void createStore_Happy_Success() throws Exception {
		final CreateStore store = CreateStore.builder()
				.name("7-Eleven")
				.phoneNumber("+1234567891")
				.category(StoreCategory.GROCERY_STORE)
				.build();

		final String createdStoreJson = mockMvc.perform(MockMvcRequestBuilders.post("/store")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(store)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		final Store createdStore = objectMapper.readValue(createdStoreJson, Store.class);
		assertNotNull(createdStore.getId());
		assertEquals("7-Eleven", createdStore.getName());
		assertEquals("+1234567891", createdStore.getPhoneNumber());
		assertEquals(StoreCategory.GROCERY_STORE, createdStore.getCategory());
		assertNull(createdStore.getEmailAddress());
	}
}
