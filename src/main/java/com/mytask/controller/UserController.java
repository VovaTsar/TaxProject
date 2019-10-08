package com.mytask.controller;

import com.mytask.domain.Customer;
import com.mytask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


    public Customer findById(Long id) {

        return userService.findById(id);
    }


    public Customer login(String email, String password) {

        return userService.login(email, password);
    }


    public void update(Customer customer) {

        userService.update(customer);
    }


//    public ArrayList<Customer> findByDepartment(Long id) {
//
//        return userService.findByDepartment(id);
//    }
//
//
//    public ArrayList<Customer> findByYear(int year) {
//
//        return userService.findByYear(year);
//    }
//
//
//    public ArrayList<Customer> findByGroup(String group) {
//
//        return userService.findByGroup(group);
//    }
//
//
//    public ArrayList<Customer> findByDepartmentAndCourse(Long id, int course) {
//        return userService.findByDepartmentAndCourse(id, course);
//    }
}
