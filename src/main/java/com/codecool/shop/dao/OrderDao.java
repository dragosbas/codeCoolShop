package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;

import java.util.HashMap;
import java.util.Map;


public interface OrderDao {

    void createOrder(Map<String, String> clientDetails, CartDao cart);

    //TODO idk how what is the parameter to get the order, probably a client id
    Order getOrder(HashMap<String, String> clientDetails);

}
