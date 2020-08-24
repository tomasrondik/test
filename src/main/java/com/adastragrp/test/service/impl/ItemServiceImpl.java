package com.adastragrp.test.service.impl;

import com.adastragrp.test.database.entity.ItemEntity;
import com.adastragrp.test.database.entity.StoreEntity;
import com.adastragrp.test.database.repository.ItemRepository;
import com.adastragrp.test.database.repository.StoreRepository;
import com.adastragrp.test.dto.CreateItem;
import com.adastragrp.test.dto.Item;
import com.adastragrp.test.service.ItemService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemRepository repository;
    private StoreRepository storeRepository;

    public ItemServiceImpl(ItemRepository repository, StoreRepository storeRepository) {
        this.repository = repository;
        this.storeRepository = storeRepository;
    }

    @Override
    public Item createItem(CreateItem item, String storeId) {
        final StoreEntity store = storeRepository.getOne(storeId);
        ItemEntity entity = dtoToEntity(item);
        entity.setStore(store);

        entity = repository.save(entity);

        return entityToDto(entity);
    }

    @Override
    public Item getItem(String id, String storeId) {
        final ItemEntity entity = repository.getByIdAndStoreId(id, storeId);
        return entityToDto(entity);
    }

    private static final ItemEntity dtoToEntity(CreateItem item) {
        return ItemEntity.builder()
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }

    private static final Item entityToDto(ItemEntity item) {
        return Item.builder()
                .id(item.getId())
                .storeId(item.getStore().getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .build();
    }
}
