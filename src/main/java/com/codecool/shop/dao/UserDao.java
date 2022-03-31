package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.User;

import java.util.UUID;

public interface UserDao {

    User addUser(String name, String password, String email, Role role);
    void removeUser(UUID id);
    void changePassword(UUID userid, String oldPassword, String newPassword);
    Cart getCart(UUID userid);
    User getUserById(UUID id);
    User getUserByName(String name);
}
