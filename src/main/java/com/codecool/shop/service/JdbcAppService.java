package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;

public class JdbcAppService  extends ApplicationService{

    protected JdbcAppService(OrderService orderService, ProductService productService, UserService userService) {
        super(orderService, productService, userService);
    }

    @Override
    public SupplierDao getSupplierDao() {
        return null;
    }

    @Override
    public ProductCategoryDao getProductCategoryDao() {
        return null;
    }

    @Override
    public ProductService getProductService() {
        return null;
    }

    @Override
    public ProductDao getProductDao() {
        return null;
    }

    @Override
    public CartDao getCartDao() {
        return null;
    }

    @Override
    public OrderService getOrderService() {
        return null;
    }

    @Override
    public UserService getUserService() {
        return null;
    }
}
