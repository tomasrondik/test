package com.adastragrp.test.service;

import com.adastragrp.test.dto.CreateItem;
import com.adastragrp.test.dto.Item;

public interface ItemService {

    Item createItem(CreateItem item, String storeId);

    Item getItem(String id, String storeId);
}
