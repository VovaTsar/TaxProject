package com.mytask.controller;

import com.mytask.domain.Customer;
import com.mytask.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MainController {
    private CustomerService customerService;

    @Autowired
    public MainController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer register(Customer customer) {
        return customerService.register(customer);
    }


    public Customer findById(Long id) {

        return customerService.findById(id);
    }

    public Customer login(String email, String password) {
        return customerService.login(email, password);
    }

    public Customer deleteById(Long id) {

        return customerService.deleteById(id);
    }


    public void update(Customer customer) {

        customerService.update(customer);
    }

    public ArrayList<Customer> findAll() {

        return customerService.findAll();
    }


//    public ArrayList<Customer> findByDepartment(Long id) {
//
//        return customerService.findByDepartment(id);
//    }
//
//
//    public ArrayList<Customer> findByYear(int year) {
//
//        return customerService.findByYear(year);
//    }
//
//
//    public ArrayList<Customer> findByGroup(String group) {
//
//        return customerService.findByGroup(group);
//    }
//
//
//    public ArrayList<Customer> findByDepartmentAndCourse(Long id, int course) {
//        return customerService.findByDepartmentAndCourse(id, course);
//    }
}
