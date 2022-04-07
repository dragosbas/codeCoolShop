package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "logOffApi", urlPatterns = "/logoff", loadOnStartup = 10)
public class UserLogOff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            HttpSession session = req.getSession();
            session.setAttribute("user-id", UUID.randomUUID());

        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}
