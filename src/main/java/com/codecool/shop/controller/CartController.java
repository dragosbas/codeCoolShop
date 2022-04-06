package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementationMem.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ApplicationService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationService service = new ApplicationService();
        CartDao cart = service.getCartDao();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        BigDecimal totalPrice = BigDecimal.ZERO;
        Map<Product, Integer> customerCart = cart.getCart(UUID.randomUUID());

        for (Product entry : customerCart.keySet()) {
            totalPrice = totalPrice.add(entry.getDefaultPrice().multiply(BigDecimal.valueOf(customerCart.get(entry))));
        }

        context.setVariable("cart", cart.getCart(UUID.randomUUID()));
        context.setVariable("totalPrice", totalPrice.toString());
        engine.process("/cart.html", context, resp.getWriter());
    }

}
