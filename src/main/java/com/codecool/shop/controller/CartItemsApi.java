package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        ApplicationService applicationService = new ApplicationService();

        CartDao cartDao =  applicationService.getCartDao();

        Map<String,String> jsonpObject = objectMapper.readValue(buffer.toString(), Map.class);
        System.out.println(jsonpObject.get("itemId"));
        ProductDao productDataStore = applicationService.getProductDao();



        HttpSession session=req.getSession();
        UUID userId = null;
        try{
            userId = UUID.fromString(session.getAttribute("user-id").toString());
            System.out.println(session.getAttribute("user-id"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        cartDao.addToCart(productDataStore.find(UUID.fromString(jsonpObject.get("itemId"))), userId);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = new ApplicationService();


        HttpSession session=req.getSession();
        UUID userId = null;
        try{
            userId = UUID.fromString(session.getAttribute("user-id").toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper= new ObjectMapper();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        Map<String,String> jsonpObject = objectMapper.readValue(buffer.toString(), Map.class);

        ProductDao productDataStore = applicationService.getProductDao();
        CartDao cartDao = applicationService.getCartDao();
//        cartDao.removeFromCart(productDataStore.find(jsonpObject.get("itemId")));
        cartDao.removeFromCart(productDataStore.find(UUID.fromString(jsonpObject.get("itemId"))), userId);
    }
}
