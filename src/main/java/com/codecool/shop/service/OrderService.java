package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;

import java.util.Map;
import java.util.UUID;

public class OrderService {
    private OrderDao orderDao;
    private CartDao cartDao;


    public OrderService() {
        this.orderDao = OrderDaoMem.getInstance();
        this.cartDao = CartDaoMem.getInstance();
    }

    public Order addOrder(Map<String, String> clientDetails, CartDao clientCart) {
        return orderDao.createOrder(clientDetails, clientCart);
    }

    public Order getOrder(UUID id) {
        return orderDao.getOrder(id);
    }

}
