package com.adastragrp.test.database.entity;

import com.adastragrp.test.domain.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "store")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "name")
    String name;

    @NotNull
    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email_address")
    String emailAddress;

    @NotNull
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    StoreCategory category;
}
