package com.codecool.shop.dao.implementationMem;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(UUID.randomUUID());
        data.add(category);
    }

    @Override
    public boolean isCategoryMissing(String name, String department, String description) {
        name = name.toLowerCase();
        department = department.toLowerCase();
        description = description.toLowerCase();
        boolean shouldAdd = true;
        for (ProductCategory productCategory : data) {
            if (productCategory.getName().equals(name) &&
                    productCategory.getDescription().equals(description) &&
                    productCategory.getDepartment().equals(department)) {
                shouldAdd = false;
                break;
            }
        }
        if (shouldAdd) {
            ProductCategory category = new ProductCategory(name.substring(0, 1).toUpperCase() + name.substring(1), department, description);
            this.add(category);
        }

        return shouldAdd;
    }



    @Override
    public ProductCategory find(UUID id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(UUID id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return data;
    }


}
