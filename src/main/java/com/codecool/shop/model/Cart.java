package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> cartItems = new ArrayList<>();

     public void add(Product product){cartItems.add(product);}
     public void remove(Product product){cartItems.remove(product);}
}
