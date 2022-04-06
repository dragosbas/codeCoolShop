package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementationMem.CartDaoMem;
import com.codecool.shop.dao.implementationMem.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "CartApi", urlPatterns = "/api/cart", loadOnStartup = 4)
public class CartItemsApi extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        Map<String,String> jsonpObject = objectMapper.readValue(buffer.toString(), Map.class);
        CartDao cartDao = CartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product p = productDataStore.find(UUID.fromString(jsonpObject.get("itemId")));
        cartDao.addToCart(p);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        Map<String,String> jsonpObject = objectMapper.readValue(buffer.toString(), Map.class);
        CartDao cartDao = CartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        cartDao.removeFromCart(productDataStore.find(jsonpObject.get("itemId")));
    }
}
