package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ApplicationService;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "loginRoute", urlPatterns = "/login", loadOnStartup = 12)
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("/login.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationService applicationService = new ApplicationService();
        UserDao userDao = applicationService.getUserDao();

        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        User attempting = userDao.getUserByName(userName);




        if(attempting != null && BCrypt.checkpw(password, attempting.getPassword())){
            HttpSession session = req.getSession();
            session.setAttribute("user-id", attempting.getId());
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect(req.getContextPath() + "/register");
        }
    }
}
