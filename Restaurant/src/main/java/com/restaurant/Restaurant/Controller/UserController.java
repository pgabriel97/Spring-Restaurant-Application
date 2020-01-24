package com.restaurant.Restaurant.Controller;

import com.restaurant.Restaurant.Model.User;
import com.restaurant.Restaurant.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @GetMapping(value = "/userList")
    public List<User> getUser() {
        return userService.findAll();
    }
    @PostMapping(value = "/register")
    public String createUser(User user) {
        userService.insertUser(user);
        return "redirect:/";
    }
    @PutMapping(value = "/updateUser")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
    @PutMapping(value = "/executeUpdateUser")
    public void executeUpdatUser(@RequestBody User user) {
        userService.executeUpdateUser(user);
    }
    @DeleteMapping(value = "/deleteEmpById")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }
}