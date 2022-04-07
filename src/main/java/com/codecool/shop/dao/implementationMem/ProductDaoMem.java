package com.codecool.shop.dao.implementationMem;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    @Override
    public Product find(String  productName) {
        return data.stream().filter(t -> t.getName().equals(productName)).findFirst().orElse(null);
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        product.setId(UUID.randomUUID());
        data.add(product);
    }


    public boolean isProductMissing(SupplierDao supplierDao, ProductCategoryDao productCategoryDao,
                                    String name, String defaultpriceInput, String defaultCurrency,
                                    String description, String productcategoryInput, String supplierInput,
                                    String img) {
        BigDecimal defaultPrice=BigDecimal.valueOf(Integer.parseInt(defaultpriceInput));
//        Currency defaultCurrency = Currency.getInstance(defaultcurrencyInput.toUpperCase());

        //verific daca exista o categorie cu numele dat; daca nu atunci return false
        ProductCategory productCategory = null;
        var categories = productCategoryDao.getAll();
        for (ProductCategory category : categories){
            if (category.getName().equalsIgnoreCase(productcategoryInput)) {
                productCategory = category;
                break;
            }
        }
        if (productCategory==null) {
            return false;
        }
        //verific daca exista o categorie cu numele dat; daca nu atunci return false
        Supplier supplier=null;
        var suppliers = supplierDao.getAll();
        for (Supplier standardSupplier : suppliers) {
            if (standardSupplier.getName().equalsIgnoreCase(supplierInput)) {
                supplier = standardSupplier;
                break;
            }
        }

        if (supplier==null) {
            return false;
        }
        //

        Product newProduct = new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier, img);
        add(newProduct);
        return true;
    }


    @Override
    public Product find(UUID id) {
        for (Product product : data) {
            if (product.getId().equals(id)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public void remove(UUID id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
