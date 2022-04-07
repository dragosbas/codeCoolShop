package com.codecool.shop.dao.implementationMem;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(UUID.randomUUID());
        data.add(supplier);
    }


    public boolean isSupplierMissing(String name) {
        name = name.toLowerCase();
        boolean shouldAdd = true;
        for (Supplier value : data) {
            if (value.getName().equals(name)) {
                shouldAdd = false;
                break;
            }
        }
        if (shouldAdd) {
            Supplier supplier = new Supplier(name);
            add(supplier);
        }

        return shouldAdd;
    }


    @Override
    public Supplier find(UUID id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(UUID id) {
        data.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }
}
