package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
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
import java.util.UUID;


@WebServlet(name = "cardPayment", urlPatterns = "/card-payment", loadOnStartup = 7)
public class CardDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = new ApplicationService();


        CartDao cart = applicationService.getCartDao();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

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

        context.setVariable("cart", cart.getCart(userId));


        context.setVariable("isRegistered", isRegistered);
        context.setVariable("isAdmin", isAdmin);

        if (session.getAttribute("user-name") != null) {
            engine.process("/product/card-payment.html", context, resp.getWriter());
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
