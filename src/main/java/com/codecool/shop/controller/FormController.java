package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Form", urlPatterns = "/checkout", loadOnStartup = 6)
public class FormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDao cart= CartDaoMem.getInstance();

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> entry : cart.getCart(0).entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getDefaultPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("totalPrice", totalPrice.toString());
        context.setVariable("cart", cart.getCart(0));

        engine.process("/product/checkout-form.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDao cart= CartDaoMem.getInstance();
        OrderDao orderDao = new OrderDaoMem();

        Map<String, String> clientDetails = new HashMap<>();
        CartDao clientCart = cart;


        clientDetails.put("First Name", req.getParameter("first-name"));
        clientDetails.put("Last Name", req.getParameter("last-name"));
        clientDetails.put("Email", req.getParameter("email"));
        clientDetails.put("Phone", req.getParameter("phone"));
        clientDetails.put("Address", req.getParameter("address"));

        orderDao.createOrder(clientDetails, cart);

        //TODO redirect to payment page
        resp.sendRedirect(req.getContextPath() + "/card-payment");


    }
}
