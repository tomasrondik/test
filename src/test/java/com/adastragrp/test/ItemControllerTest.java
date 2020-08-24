package com.adastragrp.test;

import com.adastragrp.test.dto.CreateItem;

import com.adastragrp.test.dto.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getItem_Happy_Success() throws Exception {

        final String createdItemJson = mockMvc.perform(MockMvcRequestBuilders.get("/store/uuid1/item/uuid2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        final Item createdItem = objectMapper.readValue(createdItemJson, Item.class);
        assertEquals("uuid2", createdItem.getId());
        assertEquals("uuid1", createdItem.getStoreId());
        assertEquals("Flat white", createdItem.getName());
        assertNull(createdItem.getDescription());
        assertThat(createdItem.getPrice(), is(closeTo(BigDecimal.valueOf(4.50), BigDecimal.valueOf(1E-15))));
    }

    @Test
    public void createItem_Happy_Success() throws Exception {
        final CreateItem item = CreateItem.builder()
                .name("Gingerbread latte")
                .description("The best winter season coffee ever")
                .price(BigDecimal.valueOf(7.50))
                .build();

        final String createdStoreJson = mockMvc.perform(MockMvcRequestBuilders.post("/store/uuid1/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final Item createdItem = objectMapper.readValue(createdStoreJson, Item.class);
        assertNotNull(createdItem.getId());
        assertEquals("uuid1", createdItem.getStoreId());
        assertEquals("Gingerbread latte", createdItem.getName());
        assertEquals("The best winter season coffee ever", createdItem.getDescription());
        assertThat(createdItem.getPrice(), is(closeTo(BigDecimal.valueOf(7.50), BigDecimal.valueOf(1E-15))));
    }
}
