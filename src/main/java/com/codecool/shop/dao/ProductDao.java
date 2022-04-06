package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);
    public Product find(String  productName);
    List<Product> getAll();
    List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);
    boolean isProductMissing(SupplierDao supplierDao, ProductCategoryDao productCategoryDao, String productNameInput, String defaultpriceInput, String defaultcurrencyInput, String descriptionInput, String productcategoryInput, String supplierInput, String imgInput);

}
