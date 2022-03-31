package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Role;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoMem implements UserDao {

    private List<User> users = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem() {
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public User addUser(String name, String password, String email, Role role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        users.add(user);
        return user;
    }

    @Override
    public void removeUser(UUID id) {

    }

    @Override
    public void changePassword(UUID userid, String oldPassword, String newPassword) {

    }

    @Override
    public Cart getCart(UUID userId) {
        return null;
    }

    @Override
    public User getUserById(UUID id) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        return null;
    }
}
