package com.codecool.shop.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class Cart {
    private UUID id;
    private UUID ownerId;
    private Map<Product, Integer> cartItems = new HashMap<>();

        public void add (Product product){
            if(cartItems.containsKey(product)){
                cartItems.put(product, cartItems.get(product) + 1);
            }
            else{
                cartItems.put(product, 1);
            }
        }
        public void remove (Product product){
            if(cartItems.containsKey(product) && cartItems.get(product) - 1 > 0){
                cartItems.put(product, cartItems.get(product) - 1);
            }
            else{
                cartItems.remove(product);
            }
        }
    }
