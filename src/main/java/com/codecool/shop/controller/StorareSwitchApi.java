package com.codecool.shop.controller;

import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.serializations.ProductSerialization;
import com.codecool.shop.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(name = "prodsSwitchServlet", urlPatterns = "/api/storageswitch", loadOnStartup = 10)
public class StorareSwitchApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductSerialization ps = new ProductSerialization();
        Map<String, String> params = ps.parseReqParams(req);

        ApplicationService applicationService = new ApplicationService();

        if(params.containsKey("name") && params.containsKey("password")){
            User user = applicationService.getUserDao().getUserByName(params.get("name"));

            if(user != null && user.getPassword().equals(params.get("password")) && user.getRole() == Role.ADMIN){
                DatabaseManager.switchBetweenDb_InMem();
            }
        }
    }
}
