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
    private UUID ownerId;
    private boolean orderConfirmed;
    //only for db
    private Cart cart;


    public Order (Map<String, String> clientDetails, CartDao cartDao, UUID ownerId) {
        this.clientDetails = clientDetails;
        this.cartDao = cartDao;
        this.orderConfirmed = false;
        this.orderId = UUID.randomUUID();
        this.ownerId = ownerId;
    }


    public void setOrderConfirmed(boolean b){
        orderConfirmed = b;
        cartDao.emptyCart();
    }
}
