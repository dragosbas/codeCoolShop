//package com.codecool.shop.controller;
//
//import com.codecool.shop.config.TemplateEngineUtil;
//import com.codecool.shop.dao.ProductCategoryDao;
//import com.codecool.shop.dao.ProductDao;
//import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
//import com.codecool.shop.dao.implementation.ProductDaoMem;
//import com.codecool.shop.model.Product;
////import com.codecool.shop.serializations.ProductSerialization;
//import com.codecool.shop.service.ProductService;
//import com.codecool.shop.utils.HandleApiGet;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.Serializable;
//import java.util.List;
//
//@WebServlet(urlPatterns = {"/api/get-products/"})
//public class ApiGetProducts extends HttpServlet {
//    HandleApiGet handleApiGet = new HandleApiGet();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
//        ProductSerialization serializer = new ProductSerialization();
//        StringBuilder buffer2 = new StringBuilder();
//        BufferedReader reader2 = req.getReader();
//        var categoryId = buffer2.append(reader2.readLine());
//        System.out.println("-----------------------------");
//        System.out.println(categoryId);
////        int categoryIdAsInt = (categoryId==null) ? 1 : Integer.parseInt(categoryId.toString());
//        int categoryIdAsInt = 1;
//        System.out.println(categoryId);
//        System.out.println("-----------------------------");
//
//        List<Product> products = productService.getProductsForCategory(categoryIdAsInt);
//        System.out.println(products.toString());
//        PrintWriter out = resp.getWriter();
//
//        System.out.println(serializer.serialize(products));
//        out.println(serializer.serialize(products));
//        System.out.println("-----------------------------");
////        handleApiGet.customDoGet(req,resp,"");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
//}
