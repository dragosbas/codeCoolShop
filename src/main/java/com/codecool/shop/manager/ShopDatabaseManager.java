package com.codecool.shop.manager;

import com.codecool.shop.dao.*;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ShopDatabaseManager {
    UserDao userDao;
    ProductDao productDao;
    SupplierDao supplierDao;
    ProductCategoryDao productCategoryDao;
    OrderDao orderDao;
    CartDao cartDao;

    private void setup() throws SQLException {
        DataSource dataSource = connect();
        //TODO userDao = new userDaoJdbc  etc...
    }
     

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ApplicationProperties properties = new ApplicationProperties();
        dataSource.setDatabaseName(properties.readProperty("database"));
        dataSource.setUser(properties.readProperty("user"));
        dataSource.setPassword(properties.readProperty("password"));
        System.out.println("Tying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok!");
        return dataSource;
    }

}
