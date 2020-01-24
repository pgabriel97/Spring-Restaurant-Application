package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void insertUser(User user);

    void updateUser(User user);

    void executeUpdateUser(User user);

    public void deleteUser(User user);

}
