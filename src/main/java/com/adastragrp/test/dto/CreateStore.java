package com.adastragrp.test.dto;

import com.adastragrp.test.domain.StoreCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStore {
    String name, phoneNumber, emailAddress;
    StoreCategory category;
}
