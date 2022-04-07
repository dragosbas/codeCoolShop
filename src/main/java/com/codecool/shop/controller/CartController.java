package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementationMem.CartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ApplicationService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationService service = new ApplicationService();
        CartDao cart = service.getCartDao();
//        CartDao cart= CartDaoMem.getInstance();
        ApplicationService applicationService = new ApplicationService();

        CartDao cartDao =  applicationService.getCartDao();


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        BigDecimal totalPrice = BigDecimal.ZERO;

        HttpSession session=req.getSession();

        User visitor = null;
        UUID userId = (UUID) session.getAttribute("user-id");
        if(userId != null){
            visitor = applicationService.getUserDao().getUserById(userId);
        }
        if(visitor == null){
            visitor = new User();
            if((UUID) session.getAttribute("user-id") != null){
                visitor.setId(UUID.randomUUID());
            }
            else{
                visitor.setId(UUID.randomUUID());
            }
            session.setAttribute("user-id", visitor.getId());
        }

        boolean isRegistered = visitor.getName() != null;
        boolean isAdmin = visitor.getRole() == Role.ADMIN;
        Map<Product, Integer> customerCart = cart.getCart(userId);

        for (Map.Entry<Product, Integer> entry : cartDao.getCart(userId).entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getDefaultPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }

        context.setVariable("isRegistered", isRegistered);
        context.setVariable("isAdmin", isAdmin);
        context.setVariable("cart", cartDao.getCart(userId));
        context.setVariable("totalPrice", totalPrice.toString());
        engine.process("/cart.html", context, resp.getWriter());
    }

}
