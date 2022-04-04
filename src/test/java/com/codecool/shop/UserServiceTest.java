package com.codecool.shop;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.service.UserService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class UserServiceTest {

    UserService mockUserService;
    UserDao stubUserDao;
    CartDao stubCartDao;


    @BeforeEach
    public void setup() {
        stubUserDao = mock(UserDao.class);
        stubCartDao = mock(CartDao.class);
        mockUserService = new UserService(stubUserDao, stubCartDao);
    }



}