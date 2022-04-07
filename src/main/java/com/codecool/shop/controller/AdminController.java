package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
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
import javax.swing.text.Element;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession();

        ApplicationService applicationService = new ApplicationService();
        UserDao userDao = applicationService.getUserDao();



        UUID userId = UUID.fromString(session.getAttribute("user-id").toString());

        User user =  userDao.getUserById(userId);

        if (user.getId() == UUID.fromString("b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15")) {
            engine.process("/product/admin.html", context, resp.getWriter());
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
