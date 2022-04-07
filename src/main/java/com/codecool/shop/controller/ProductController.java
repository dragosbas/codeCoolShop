package com.codecool.shop.controller;

import com.codecool.shop.model.User;
import com.codecool.shop.service.ApplicationService;
import com.codecool.shop.config.TemplateEngineUtil;
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

        UUID userId = null;
        try{

            HttpSession session = req.getSession();
            session.setAttribute("user-id", UUID.randomUUID());
            System.out.println(session.getAttribute("user-id"));
            userId = UUID.fromString(session.getAttribute("user-id").toString());


        }catch(Exception e){System.out.println(e);}

        User visitor = applicationService.getUserDao().getUserById(userId);

        boolean isRegistered = visitor.getName() != null;


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("suppliers", applicationService.getSupplierDao().getAll());

        context.setVariable("categories", applicationService.getProductCategoryDao().getAll());

        context.setVariable("category", applicationService.getProductCategoryDao().find(userId));

        context.setVariable("products", applicationService.getProductDao().getAll());

        engine.process("product/index.html", context, resp.getWriter());
    }

}
