package com.adastragrp.test.controller;

import com.adastragrp.test.dto.CreateStore;
import com.adastragrp.test.dto.Store;
import com.adastragrp.test.service.StoreService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    private StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @GetMapping(path = "/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Store getStore(@PathVariable("storeId") String storeId) {
        return service.getStore(storeId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Store createStore(@RequestBody CreateStore store) {
        return service.create(store);
    }
}
