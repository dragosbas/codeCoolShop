package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;

import com.codecool.shop.dao.implementationMem.CartDaoMem;
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
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "Form", urlPatterns = "/checkout", loadOnStartup = 6)
public class FormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDao cart= CartDaoMem.getInstance();

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> entry : cart.getCart(UUID.randomUUID()).entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getDefaultPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("totalPrice", totalPrice.toString());
        context.setVariable("cart", cart.getCart(UUID.randomUUID()));


        engine.process("/product/checkout-form.html", context, resp.getWriter());

    }


}
