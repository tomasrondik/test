package com.adastragrp.test.controller;

import com.adastragrp.test.dto.CreateItem;
import com.adastragrp.test.dto.Item;
import com.adastragrp.test.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/{storeId}/item")
public class ItemController {

    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable String storeId, @PathVariable String itemId) {
        return service.getItem(itemId, storeId);
    }

    @PostMapping
    public Item createItem(@PathVariable String storeId, @RequestBody CreateItem item) {
        return service.createItem(item, storeId);
    }
}
