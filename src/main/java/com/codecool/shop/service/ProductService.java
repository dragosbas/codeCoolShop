package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class ProductService{
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }


    public List<Product> getProductsForSupplier(int id) {
        var supplier = supplierDao.find(id);
        return productDao.getBy(supplier);
    }

    public boolean addSupplier(String name, String description){
        name = name.toLowerCase();
        description = description.toLowerCase();
        boolean shouldAdd = true;
        var suppliers = supplierDao.getAll();
        for (Supplier value : suppliers) {
            if (value.getName().equals(name) &&
                    value.getDescription().equals(description)) {
                shouldAdd = false;
                break;
            }
        }
        if (shouldAdd) {
            Supplier supplier = new Supplier(name, description);
            supplierDao.add(supplier);
        }

        return shouldAdd;
    }

    public boolean addProductCategory(String name, String department, String description){
        name = name.toLowerCase();
        department = department.toLowerCase();
        description = description.toLowerCase();
        boolean shouldAdd = true;
        var categories = productCategoryDao.getAll();
        for (ProductCategory productCategory : categories) {
            if (productCategory.getName().equals(name) &&
                    productCategory.getDescription().equals(description) &&
                    productCategory.getDepartment().equals(department)) {
                shouldAdd = false;
                break;
            }
        }
        if (shouldAdd) {
            ProductCategory category = new ProductCategory(name.substring(0, 1).toUpperCase() + name.substring(1), department, description);
            productCategoryDao.add(category);
        }

        return shouldAdd;
    }
}
