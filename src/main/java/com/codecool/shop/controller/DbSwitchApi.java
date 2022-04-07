package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;


@WebServlet(name = "dbSwitchApi", urlPatterns = "/api/dbswitch", loadOnStartup = 9)
public class DbSwitchApi extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        ApplicationService applicationService = new ApplicationService();

        HttpSession session=req.getSession();
        UUID userId = null;
        try{
            userId = UUID.fromString((String) session.getAttribute("user-id"));
            System.out.println(session.getAttribute("user-id"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        UserDao userDao = applicationService.getUserDao();

        User user =  userDao.getUserById(userId);



        if (user.getId() == UUID.fromString("b0eebc93-9c0b-4ef8-bb6d-6bb9bd380a15")){
            DatabaseManager.switchBetweenDb_InMem();
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }

    }
}