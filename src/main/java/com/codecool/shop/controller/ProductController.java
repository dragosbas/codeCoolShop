package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ApplicationService;
import com.codecool.shop.config.TemplateEngineUtil;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = new ApplicationService();
        UserDao userDao =  applicationService.getUserDao();

        HttpSession session = req.getSession();
        User visitor = null;
        UUID userId = (UUID) session.getAttribute("user-id");
        if(userId != null){
             visitor = applicationService.getUserDao().getUserById(userId);
        }
        if(visitor == null){
            visitor = new User();
            if((UUID) session.getAttribute("user-id") != null){
                visitor.setId((UUID) session.getAttribute("user-id"));
            }
            else{
                visitor.setId(UUID.randomUUID());
            }
            session.setAttribute("user-id", visitor.getId());
        }

        boolean isRegistered = visitor.getName() != null;
        boolean isAdmin = visitor.getRole() == Role.ADMIN;

        if (session.getAttribute("user-name") == null) {

            userDao.addUser(null, null, null, null, visitor.getId());
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("isRegistered", isRegistered);
        context.setVariable("isAdmin", isAdmin);

        context.setVariable("suppliers", applicationService.getSupplierDao().getAll());

        context.setVariable("categories", applicationService.getProductCategoryDao().getAll());

        context.setVariable("category", applicationService.getProductCategoryDao().find(userId));

        context.setVariable("products", applicationService.getProductDao().getAll());

        engine.process("product/index.html", context, resp.getWriter());
    }

}
