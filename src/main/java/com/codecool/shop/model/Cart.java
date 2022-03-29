package com.codecool.shop.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    List<Product> cartItems = new ArrayList<>();

    public Cart() {

            cartItems.add(new Product("chiloti",
                    BigDecimal.valueOf(10),
                    "USD",
                    "cei mai curati ever !",
                    new ProductCategory("haine", "straie", "textle"),
                    new Supplier("Versace","scumpi"),
                    "/"));
            cartItems.add(new Product("maieu",
                    BigDecimal.valueOf(20),
                    "USD",
                    "gri !",
                    new ProductCategory("haine", "straie", "textle"),
                    new Supplier("Vaslui","ieftini"),
                    "/"));
        }

        public void add (Product product){
            cartItems.add(product);
        }
        public void remove (Product product){
            cartItems.remove(product);
        }
    }
