package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import lombok.Data;

import java.util.*;

@Data
public class OrderDaoMem  implements OrderDao {
   List<Order> orders = new ArrayList<>();
   private static OrderDaoMem instance = null;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    private OrderDaoMem(){

    }


    @Override
    public Order createOrder(Map<String, String> clientDetails, CartDao cart) {
        Order order = new Order(clientDetails, cart);
        orders.add(order);
        return order;

    }

    @Override
    public Order getOrder(UUID orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId)
                return order;
        }
        return null;
    }

    public void confirmOrder() {
//        order.setOrderConfirmed(true);
    }


}
