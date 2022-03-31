package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public interface OrderDao {

    Order createOrder(Map<String, String> clientDetails, CartDao cart);

    //TODO idk how what is the parameter to get the order, probably a client id
    Order getOrder(UUID orderID);

    void confirmOrder();

}
