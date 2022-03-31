package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;

import java.util.Map;
import java.util.UUID;

public class UserService {

    private final UserDao userDao;
    private final CartDao cartDao;

    public UserService(UserDao userDao, CartDao cartDao) {
        this.userDao = userDao;
        this.cartDao = cartDao;
    }

    public User getUser(UUID id){
        return userDao.getUserById(id);
    }

    public User getUser(String name){
        return userDao.getUserByName(name);
    }

    //todo change to return cart after changing the cart to be a standalone object instead of a singleton
    public Map<Product, Integer> getUserCart(UUID userId){
        return cartDao.getCart(userId);
    }

    public void addUser(String name, String password, String email, boolean isAdmin){
        Role role = isAdmin ? Role.ADMIN : Role.USER;
        userDao.addUser(name, password, email, role);
    }
}
