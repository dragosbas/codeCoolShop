package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

import com.codecool.shop.dao.CartDao;
import lombok.Data;


@Data
public class Order {
    private Map<String, String> clientDetails = new HashMap<>();
    private CartDao cart;
    // TODO create an id based on something
    private int orderId;


    public void createOrder(Map<String, String> clientDetails, CartDao cart) {
        this.clientDetails = clientDetails;
        this.cart = cart;
    }
}
