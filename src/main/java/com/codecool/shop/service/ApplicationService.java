package com.codecool.shop.service;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementationJdbc.CartDaoJdbc;
import com.codecool.shop.dao.implementationMem.*;
import com.codecool.shop.manager.ShopDatabaseManager;
import com.codecool.shop.utils.Persistence;

import javax.sql.DataSource;
import java.sql.SQLException;

public  class ApplicationService {
    private static ApplicationService instance = null;

    CartDao cartDao;
    OrderDao orderDao;
    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    UserDao userDao;
    DataSource dataSource;

    private Persistence persistence;

    private ApplicationService() {

    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }
        return instance;
    }

    public Persistence getPersistence() {
        return persistence;
    }

    public void setPersistence(Persistence persistence) {
        this.persistence = persistence;
    }

    public void setApplicationService() {

        if (persistence == Persistence.JDBC) {
            //todo CategoryDaoJdbc si restul ca singletone
            //todo DatabaseManager
            ShopDatabaseManager shopDatabaseManager = new ShopDatabaseManager();
            try {
                this.dataSource = shopDatabaseManager.connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            cartDao = CartDaoJdbc.getInstance();
//            orderDao = OrderDaoJdbc.getInstance();
//            productCategoryDao = ProductCategoryDaoJdbc.getInstance();
//            productDao = ProductDaoJdbc.getInstance();
//            supplierDao = SupplierDaoJdbc.getInstance();
//            userDao = UserDaoJdbc.getInstance();
            orderDao = OrderDaoMem.getInstance();
            productCategoryDao = ProductCategoryDaoMem.getInstance();
            productDao = ProductDaoMem.getInstance();
            supplierDao = SupplierDaoMem.getInstance();
            userDao = UserDaoMem.getInstance();

            establishConnection();

        } else if (persistence == Persistence.MEMORY) {

            cartDao = CartDaoMem.getInstance();
            orderDao = OrderDaoMem.getInstance();
            productCategoryDao = ProductCategoryDaoMem.getInstance();
            productDao = ProductDaoMem.getInstance();
            supplierDao = SupplierDaoMem.getInstance();
            userDao = UserDaoMem.getInstance();



        }

    }

    private void establishConnection() {
        ((CartDaoJdbc)cartDao).establishConnection(dataSource);
//        ((OrderDaoJdbc)orderDao).establishConnection(dataSource);
//        ((productCategoryDaoJdbc)productCategoryDao).establishConnection(dataSource);
//        ((supplierDaoJdbc)supplierDao).establishConnection(dataSource);
//        ((productDaoJdbc)productDao).establishConnection(dataSource);
//        ((supplierDaoJdbc)supplierDao).establishConnection(dataSource);
//        ((userDaoJdbc)userDao).establishConnection(dataSource);
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
