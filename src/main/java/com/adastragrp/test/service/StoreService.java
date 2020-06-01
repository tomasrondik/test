package com.adastragrp.test.service;

import com.adastragrp.test.dto.CreateStore;
import com.adastragrp.test.dto.Store;

public interface StoreService {

    Store create(CreateStore store);

    Store getStore(String id);
}
