package com.restaurant.Restaurant.Interface;

import com.restaurant.Restaurant.Model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    void insertUser(User user);

    void updateUser(User user);

    void executeUpdateUser(User user);

    public void deleteUser(User user);

}
