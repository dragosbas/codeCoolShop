package com.codecool.shop.factories;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementationMem.*;
import com.codecool.shop.service.*;

public class ApplicationServiceFactory {

    public ApplicationService getApplicationService(boolean isDatabaseOn) {
        if (isDatabaseOn) {
            return null;
        } else {
            CartDao cartDao = CartDaoMem.getInstance();
            OrderDao orderDao = OrderDaoMem.getInstance();
            ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
            ProductDao productDao = ProductDaoMem.getInstance();
            SupplierDao supplierDao = SupplierDaoMem.getInstance();
            UserDao userDao = UserDaoMem.getInstance();


            OrderService orderService = new OrderService(orderDao, cartDao);
            ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);
            UserService userService = new UserService(userDao, cartDao);

            return new InMemAppService(orderService, productService, userService);
        }
    }

}
