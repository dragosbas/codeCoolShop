package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;

public interface CartDao {
    
    public void addToCart(Product product);
    public void removeFromCart(Product product);
    public Map<Product, Integer> getCart(int id);

}
