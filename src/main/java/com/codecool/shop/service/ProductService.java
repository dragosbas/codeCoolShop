package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;
    private final SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }


    public List<Product> getProductsForSupplier(int id) {
        var supplier = supplierDao.find(id);
        return productDao.getBy(supplier);
    }


    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    public boolean addSupplier(String name) {
        name = name.toLowerCase();
        boolean shouldAdd = true;
        var suppliers = supplierDao.getAll();
        for (Supplier value : suppliers) {
            if (value.getName().equals(name)) {
                shouldAdd = false;
                break;
            }
        }
        if (shouldAdd) {
            Supplier supplier = new Supplier(name);
            supplierDao.add(supplier);
        }

        return shouldAdd;
    }

    public boolean addProductCategory(String name, String department, String description) {
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

    public boolean addProduct(String productNameInput,String defaultpriceInput, String defaultcurrencyInput,String descriptionInput, String productcategoryInput, String supplierInput, String imgInput) {
        BigDecimal defaultPrice=BigDecimal.valueOf(Integer.parseInt(defaultpriceInput));
//        Currency defaultCurrency = Currency.getInstance(defaultcurrencyInput.toUpperCase());
        String defaultCurrency = defaultcurrencyInput;

        //verific daca exista o categorie cu numele dat; daca nu atunci return false
        ProductCategory productCategory = null;
        var categories = productCategoryDao.getAll();
        for (ProductCategory category : categories) if (category.getName().equals(productcategoryInput)) productCategory = category;
        if (productCategory==null) return false;
        //verific daca exista o categorie cu numele dat; daca nu atunci return false
        Supplier supplier=null;
        var suppliers = supplierDao.getAll();
        for (Supplier standardSupplier : suppliers) if (standardSupplier.getName().equals(supplierInput)) supplier = standardSupplier;
        if (supplier==null) return false;
        //

        String img = imgInput;
        String name = productNameInput;
        String description = descriptionInput;
        Product newProduct = new Product(name,defaultPrice,defaultCurrency,description,productCategory,supplier,img);
        productDao.add(newProduct);
        return true;
    }
}
