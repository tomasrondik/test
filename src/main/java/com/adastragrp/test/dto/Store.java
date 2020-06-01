package com.adastragrp.test.dto;

import com.adastragrp.test.domain.StoreCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Store {
    String id, name, phoneNumber, emailAddress;
    StoreCategory category;
}
