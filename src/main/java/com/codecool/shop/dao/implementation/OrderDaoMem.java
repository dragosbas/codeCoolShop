package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class OrderDaoMem  implements OrderDao {
    Order order = new Order();

    @Override
    public void createOrder(Map<String, String> clientDetails, CartDao cart) {
        order.createOrder(clientDetails, cart);
    }

    @Override
    public Order getOrder(HashMap<String, String> clientDetails) {
        return order;
    }
}
