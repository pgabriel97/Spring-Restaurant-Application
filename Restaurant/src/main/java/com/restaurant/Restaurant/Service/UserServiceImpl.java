package com.restaurant.Restaurant.Service;

import com.restaurant.Restaurant.Interface.UserDao;
import com.restaurant.Restaurant.Model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserServiceImpl implements UserService{
    @Resource
    UserDao userDao;
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);

    }
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);

    }
    @Override
    public void executeUpdateUser(User user) {
        userDao.executeUpdateUser(user);

    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);

    }
}
