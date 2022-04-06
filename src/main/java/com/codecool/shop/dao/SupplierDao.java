package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(int id);
    void remove(int id);

    boolean isSupplierMissing(String name);

    List<Supplier> getAll();
}
