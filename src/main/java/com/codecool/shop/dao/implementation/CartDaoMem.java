package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CartDaoMem implements CartDao {
    private Cart cart = new Cart();

    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    private CartDaoMem() {
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
    public Map<Product, Integer> getCart(UUID id) {
        return cart.getCartItems();
    }

    @Override
    public void emptyCart() {
        cart = new Cart();
    }
}
