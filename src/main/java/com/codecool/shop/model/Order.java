package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.codecool.shop.dao.CartDao;
import lombok.Data;


@Data
public class Order {
    private Map<String, String> clientDetails;
    private CartDao cartDao;
    // TODO create an id based on something
    private UUID orderId;
    private boolean orderConfirmed;


    public Order (Map<String, String> clientDetails, CartDao cartDao) {
        this.clientDetails = clientDetails;
        this.cartDao = cartDao;
        this.orderConfirmed = false;
        this.orderId = UUID.randomUUID();
    }
}
