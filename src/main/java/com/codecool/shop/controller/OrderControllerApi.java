package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.utils.LoggerService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "orderApi", urlPatterns = "/order/api", loadOnStartup = 9)
public class OrderControllerApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("first-name") != null) {

            CartDao cart= CartDaoMem.getInstance();
            OrderService orderService = new OrderService();

            Map<String, String> clientDetails = new HashMap<>();
            CartDao clientCart = cart;


            clientDetails.put("First Name", req.getParameter("first-name"));
            clientDetails.put("Last Name", req.getParameter("last-name"));
            clientDetails.put("Email", req.getParameter("email"));
            clientDetails.put("Phone", req.getParameter("phone"));
            clientDetails.put("Address", req.getParameter("address"));

            Order order = orderService.addOrder(clientDetails, cart);


            try{

                HttpSession session=req.getSession();
                session.setAttribute("order-id", order.getOrderId());


            }catch(Exception e){System.out.println(e);}


            resp.sendRedirect(req.getContextPath() + "/card-payment");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session=req.getSession();
        UUID orderId = (UUID) session.getAttribute("order-id");
        LoggerService l = LoggerService.getInstance();

        OrderService orderService = new OrderService();

        Order currentOrder = orderService.getOrder(orderId);

        //TODO add to DB, next sprint
        l.log(currentOrder);
        currentOrder.setOrderConfirmed(true);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("Success","Yes");


        resp.sendRedirect(req.getContextPath() + "/success");
    }
}
