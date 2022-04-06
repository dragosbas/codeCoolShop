package com.codecool.shop.dao.implementationJdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementationMem.OrderDaoMem;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.util.Map;
import java.util.UUID;

public class CartDaoJdbc implements CartDao {
    private DataSource dataSource;
    private static CartDaoJdbc instance = null;

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }


    private CartDaoJdbc() {

    }

    public void establishConnection(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public void addToCart(Product product) {

    }

    @Override
    public void removeFromCart(Product product) {

    }

    @Override
    public Map<Product, Integer> getCart(UUID id) {
        return null;
    }

    @Override
    public void emptyCart() {

    }
}
