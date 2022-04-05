package com.codecool.shop.service;

import com.codecool.shop.dao.*;

public abstract class ApplicationService {
        OrderService orderService;
        ProductService productService;
        UserService userService;

//        boolean databaseActive;

    protected ApplicationService(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    public abstract SupplierDao getSupplierDao();

    public abstract ProductCategoryDao getProductCategoryDao();

    public abstract ProductDao getProductDao();

    public abstract CartDao getCartDao();

    public abstract ProductService getProductService();

    public abstract OrderService getOrderService();

    public abstract UserService getUserService();

}
