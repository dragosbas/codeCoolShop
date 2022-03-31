package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.codecool.shop.dao.CartDao;
import lombok.Data;


@Data
public class Order {
    private Map<String, String> clientDetails;
    private CartDao cart;
    // TODO create an id based on something
    private UUID orderId;
    private boolean orderConfirmed;


    public Order (Map<String, String> clientDetails, CartDao cart) {
        this.clientDetails = clientDetails;
        this.cart = cart;
        this.orderConfirmed = false;
        this.orderId = UUID.randomUUID();
    }
}
