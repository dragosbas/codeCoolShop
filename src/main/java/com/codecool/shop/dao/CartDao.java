package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CartDao {
    
    public void addToCart(Product product);
    public void removeFromCart(Product product);
    public Map<Product, Integer> getCart(UUID id);
    void emptyCart();

}
