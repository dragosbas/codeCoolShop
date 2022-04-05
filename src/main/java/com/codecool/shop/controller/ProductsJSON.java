package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementationMem.*;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;
import com.codecool.shop.serializations.ProductSerialization;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet(name = "productsServlet", urlPatterns = "/api/products/product", loadOnStartup = 7)
public class ProductsJSON extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// ma astept sa primesc :
// productNameInput
// defaultpriceInput
// defaultcurrencyInput
// descriptionInput
// productcategoryInput
// supplierInput
// imgInput
        ProductSerialization ps = new ProductSerialization();
        Map<String, String> params = ps.parseReqParams(req);

        UserDao userDao = UserDaoMem.getInstance();
        CartDao cartDao = CartDaoMem.getInstance();
        UserService users = new UserService(userDao, cartDao);
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDao);
        boolean added = false;

        System.out.println(params);

        if (params.containsKey("name") && params.containsKey("password")) {
            User user = users.getUser(params.get("name"));

            if (user != null && user.getPassword().equals(params.get("password")) && user.getRole() == Role.ADMIN) {
                added = productService.addProduct(params.get("productname"), params.get("defaultprice"),
                        params.get("defaultCurrency"), params.get("description"),params.get("productCategory"),
                        params.get("supplier"), params.get("img"));
            }
        }

        PrintWriter out = resp.getWriter();

        if (added) {
            out.println(HttpServletResponse.SC_ACCEPTED);
        } else {
            out.println(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
}
