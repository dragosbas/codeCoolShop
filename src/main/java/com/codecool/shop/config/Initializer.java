package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementationMem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementationMem.ProductDaoMem;
import com.codecool.shop.dao.implementationMem.SupplierDaoMem;
import com.codecool.shop.dao.implementationMem.UserDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ApplicationService;
import com.codecool.shop.utils.Persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.util.UUID;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        UserDao userDaoStore= UserDaoMem.getInstance();

        // SA NU UITAM!
        // 1 Iei instanceul
        // 2 Setezi persistenta
        // 3 Initializezi Dao-urile in funcite de persistena setata la 2!
        ApplicationService applicationService = new ApplicationService();
//        applicationService.setPersistence(Persistence.JDBC);
//        applicationService.setApplicationService();


        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop","Hardware","Sisteme de calcul avansate portabile");
        productCategoryDataStore.add(laptop);
        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "/static/img/product_1.jpg"));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo, "/static/img/product_2.jpg"));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "/static/img/product_3.jpg"));
        productDataStore.add(new Product("MacBook Noob",new BigDecimal("1200"),"USD","Laptop pt Robert wannabee s",laptop,amazon,"https://lcdn.altex.ro/resize/media/catalog/product/M/a/2bd48d28d1c32adea0e55139a4e6434a/MacBook_Pro_13in_Silver-1.jpg"));
        productDataStore.add(new Product("Lenovo",new BigDecimal("1000"),"USD","Laptopul praf al lui Dragos",laptop,amazon,"https://cdn.pocket-lint.com/r/s/1200x/assets/images/135517-laptops-review-lenovo-y50-review-image4-vnGZ3qtRKT.jpg"));
        //creating an admin -> pw: codecool
        userDaoStore.addUser("admin", "$2a$12$IJD22UF0MSImWbTvd4OMbOsUo8B6SpqcDJ04EzlA.xM7hzoah5bW.", "admin@email.com", Role.ADMIN, UUID.randomUUID());
    }
}
