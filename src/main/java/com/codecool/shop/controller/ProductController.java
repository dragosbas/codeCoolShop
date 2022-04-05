package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementationMem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementationMem.ProductDaoMem;
import com.codecool.shop.dao.implementationMem.SupplierDaoMem;
import com.codecool.shop.service.ApplicationService;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDao= SupplierDaoMem.getInstance();
//        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDao);
//        ApplicationServiceFactory applicationServiceFactory = new ApplicationServiceFactory();
//        ApplicationService applicationService = applicationServiceFactory.getApplicationService(false);
//
//        ProductDao productDao   = applicationService.getProductDao();
//        ProductCategoryDao  productCategoryDataStore = applicationService.getProductCategoryDao();
//        SupplierDao supplierDao = applicationService.getSupplierDao();
//        ProductService productService = applicationService.getProductService();
//        OrderService orderService = applicationService.getOrderService();

        ApplicationService applicationService = ApplicationService.getInstance();


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("suppliers", supplierDao.getAll());
        context.setVariable("suppliers", applicationService.getSupplierDao().getAll());
//        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("categories", applicationService.getProductCategoryDao().getAll());

//        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("category", applicationService.getProductCategoryDao().find(1));
//        context.setVariable("products", productService.getAllProducts());
        context.setVariable("products", applicationService.getProductDao().getAll());
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
