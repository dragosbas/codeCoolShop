package com.codecool.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "logOffApi", urlPatterns = "/logoff", loadOnStartup = 10)
public class UserLogOff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            HttpSession session = req.getSession();
            session.removeAttribute("user-name");
            session.removeAttribute("user-id");
            resp.sendRedirect(req.getContextPath() + "/");

        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}
