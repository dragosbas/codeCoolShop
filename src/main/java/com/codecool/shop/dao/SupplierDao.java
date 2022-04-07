package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(UUID id);
    void remove(UUID id);

    boolean isSupplierMissing(String name);

    List<Supplier> getAll();
}
