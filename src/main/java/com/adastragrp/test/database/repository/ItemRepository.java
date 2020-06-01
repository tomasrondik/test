package com.adastragrp.test.database.repository;

import com.adastragrp.test.database.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {

    ItemEntity getByIdAndStoreId(String id, String storeId);
}
