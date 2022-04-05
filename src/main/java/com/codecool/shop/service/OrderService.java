package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementationMem.CartDaoMem;
import com.codecool.shop.dao.implementationMem.OrderDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.Map;
import java.util.UUID;

public class OrderService {
    private OrderDao orderDao;
    private CartDao cartDao;


    public OrderService(OrderDao orderDao, CartDao cartDao) {
//        this.orderDao = OrderDaoMem.getInstance();
//        this.cartDao = CartDaoMem.getInstance();
        this.orderDao = orderDao;
        this.cartDao = cartDao;
    }

    public Order addOrder(Map<String, String> clientDetails, CartDao clientCart) {
        return orderDao.createOrder(clientDetails, clientCart);
    }

    public Order getOrder(UUID id) {
        return orderDao.getOrder(id);
    }

//    public Map<Product, Integer> getCart(UUID id) {return cartDao.getCart(id);}

}
