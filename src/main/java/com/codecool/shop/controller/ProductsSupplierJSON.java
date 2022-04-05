package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementationMem.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.serializations.ProductSerialization;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;


@WebServlet(name = "prodsBySupplierServlet", urlPatterns = "/api/products/supplier", loadOnStartup = 3)
public class ProductsSupplierJSON extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String linkId = request.getParameter("id");
        Integer id = null;

        try {
            id = Integer.parseInt(linkId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (id == null || id < 1) {
            id = 1;
        }
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao= SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);

        String products = writeListToJsonArray(productService.getProductsForSupplier(id));
        System.out.println(products);
        PrintWriter out = response.getWriter();
        out.println(products);
    }

    public String writeListToJsonArray(List<Product> products) throws IOException {
        final StringWriter sw = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, products);
        sw.close();

        return sw.toString();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductSerialization ps = new ProductSerialization();
        Map<String, String> params = ps.parseReqParams(req);

        UserDao userDao = UserDaoMem.getInstance();
        CartDao cartDao = CartDaoMem.getInstance();
        UserService users = new UserService(userDao, cartDao);
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao= SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDao);
        boolean added = false;

        if(params.containsKey("name") && params.containsKey("password")){
            User user= users.getUser(params.get("name"));

            if(user != null && user.getPassword().equals(params.get("password")) && user.getRole() == Role.ADMIN){
                added = productService.addSupplier(params.get("supplier"));
            }
        }

        PrintWriter out = resp.getWriter();

        if(added){
            out.println(HttpServletResponse.SC_ACCEPTED);
        }
        else{
            out.println(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}
