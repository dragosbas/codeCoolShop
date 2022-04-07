package com.codecool.shop.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
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
import java.util.Arrays;
import java.util.UUID;

@WebServlet(name = "registerApi", urlPatterns = "/register", loadOnStartup = 9)
public class RegisterApi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("/product/register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationService applicationService = new ApplicationService();
        UserDao userDao = applicationService.getUserDao();

        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        char[] charPassword = password.toCharArray();
        var charHashedPassword = BCrypt.withDefaults().hash(5, charPassword);
        String hashedPassword = charHashedPassword.toString();

        HttpSession session = req.getSession();
        UUID userId = null;
        try{
            userId = UUID.fromString(session.getAttribute("user-id").toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        userDao.addUser(userName,hashedPassword,email, Role.USER,userId);
        System.out.println("current user id = " + userId);
        resp.sendRedirect(req.getContextPath() + "/");

    }
}
