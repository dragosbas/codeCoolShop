package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

public class CartDaoMem implements CartDao {
    private final Cart cart = new Cart();

    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void addToCart(Product product) {
        cart.add(product);
    }

    @Override
    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    @Override
    public Cart getCart(int id) {
        return cart;
    }
}
