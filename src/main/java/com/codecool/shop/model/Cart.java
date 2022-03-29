package com.codecool.shop.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cart {

    private Map<Product, Integer> cartItems = new HashMap<>();

    public Cart() {

            cartItems.put(new Product("chiloti",
                    BigDecimal.valueOf(10),
                    "USD",
                    "cei mai curati ever !",
                    new ProductCategory("haine", "straie", "textle"),
                    new Supplier("Versace","scumpi"),
                    "/"), 1);
            cartItems.put(new Product("maieu",
                    BigDecimal.valueOf(20),
                    "USD",
                    "gri !",
                    new ProductCategory("haine", "straie", "textle"),
                    new Supplier("Vaslui","ieftini"),
                    "/"), 1);
        }

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
                cartItems.remove(product);;
            }
        }
    }
