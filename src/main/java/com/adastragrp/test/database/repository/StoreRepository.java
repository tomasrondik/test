package com.adastragrp.test.database.repository;

import com.adastragrp.test.database.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, String> {
}
