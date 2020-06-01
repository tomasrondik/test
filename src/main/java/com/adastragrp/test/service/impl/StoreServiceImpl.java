package com.adastragrp.test.service.impl;

import com.adastragrp.test.database.entity.StoreEntity;
import com.adastragrp.test.database.repository.StoreRepository;
import com.adastragrp.test.dto.CreateStore;
import com.adastragrp.test.dto.Store;
import com.adastragrp.test.service.StoreService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    private StoreRepository repository;

    public StoreServiceImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Store create(CreateStore store) {
        var entity = dtoToEntity(store);
        entity = repository.save(entity);

        return entityToDto(entity);
    }

    @Override
    public Store getStore(String id) {
        final var entity = repository.getOne(id);
        return entityToDto(entity);
    }

    private static final Store entityToDto(StoreEntity entity) {
        return Store.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .emailAddress(entity.getEmailAddress())
                .category(entity.getCategory())
                .build();
    }

    private static final StoreEntity dtoToEntity(CreateStore dto) {
        // TODO create null pointer situation by adding Address
        return StoreEntity.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .emailAddress(dto.getEmailAddress())
                .category(dto.getCategory())
                .build();
    }
}
