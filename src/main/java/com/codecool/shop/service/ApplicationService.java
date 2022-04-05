package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementationJdbc.CartDaoJdbc;
import com.codecool.shop.dao.implementationMem.*;

public  class ApplicationService {
    private static ApplicationService instance = null;

    CartDao cartDao;
    OrderDao orderDao;
    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    UserDao userDao;

    private boolean isDatabaseOn = false;

    private ApplicationService() {

    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public boolean isDatabaseOn() {
        return isDatabaseOn;
    }

    public void setDatabaseOn(boolean databaseOn) {
        isDatabaseOn = databaseOn;
    }

    public void setApplicationService() {

        if (isDatabaseOn) {
            //todo CategoryDaoJdbc si restul ca singletone
            //todo DatabaseManager

            cartDao = CartDaoMem.getInstance();
            orderDao = OrderDaoMem.getInstance();
            productCategoryDao = ProductCategoryDaoMem.getInstance();
            productDao = ProductDaoMem.getInstance();
            supplierDao = SupplierDaoMem.getInstance();
            userDao = UserDaoMem.getInstance();

//            ((CartDaoJdbc)cartDao).setConnection();
        } else {

            cartDao = CartDaoMem.getInstance();
            orderDao = OrderDaoMem.getInstance();
            productCategoryDao = ProductCategoryDaoMem.getInstance();
            productDao = ProductDaoMem.getInstance();
            supplierDao = SupplierDaoMem.getInstance();
            userDao = UserDaoMem.getInstance();


//            OrderService orderService = new OrderService(orderDao, cartDao);
//            ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);
//            UserService userService = new UserService(userDao, cartDao);

//            return new InMemAppService(orderService, productService, userService);
            // plus setConnection pentru fiecare jdbc
        }

    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
