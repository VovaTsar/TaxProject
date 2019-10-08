package com.mytask.controller;

import com.mytask.domain.customer.Customer;
import com.mytask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    public Customer register(Customer customer) {

        return userService.register(customer);
    }

    public Customer login(String email, String password) {

        return userService.login(email, password);
    }


    public Customer findById(Long id) {

        return userService.findById(id);
    }



    public void update(Customer customer) {

        userService.update(customer);
    }



}
