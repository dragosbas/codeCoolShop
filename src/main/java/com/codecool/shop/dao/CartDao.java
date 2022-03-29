package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.List;

public interface CartDao {
    
    public void addToCart(Product product);
    public void removeFromCart(Product product);
    public Cart getCart(int id);

}
