package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementationMem.CartDaoMem;
import com.codecool.shop.dao.implementationMem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementationMem.ProductDaoMem;
import com.codecool.shop.dao.implementationMem.SupplierDaoMem;

public class InMemAppService extends ApplicationService {


    public InMemAppService(OrderService orderService, ProductService productService, UserService userService) {
        super(orderService, productService, userService);

    }

    @Override
    public SupplierDao getSupplierDao() {
        return SupplierDaoMem.getInstance();
    }

    @Override
    public ProductCategoryDao getProductCategoryDao() {
        return ProductCategoryDaoMem.getInstance();
    }

    @Override
    public ProductDao getProductDao() {
        return ProductDaoMem.getInstance();
    }

    @Override
    public CartDao getCartDao() {
        return CartDaoMem.getInstance();
    }


    @Override
    public  ProductService getProductService() {
        return productService;
    }

    @Override
    public OrderService getOrderService() {
        return orderService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

}
