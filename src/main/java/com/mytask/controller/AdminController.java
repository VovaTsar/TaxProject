package com.mytask.controller;

import com.mytask.domain.Customer;
import com.mytask.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public Customer register(Customer customer) {
        return adminService.register(customer);
    }


    public Customer findById(Long id) {

        return adminService.findById(id);
    }

    public Customer login(String email, String password) {
        return adminService.login(email, password);
    }

    public Customer deleteById(Long id) {

        return adminService.deleteById(id);
    }


    public void update(Customer customer) {

        adminService.update(customer);
    }

    public ArrayList<Customer> findAll() {

        return adminService.findAll();
    }


//    public ArrayList<Customer> findByDepartment(Long id) {
//
//        return adminService.findByDepartment(id);
//    }
//
//
//    public ArrayList<Customer> findByYear(int year) {
//
//        return adminService.findByYear(year);
//    }
//
//
//    public ArrayList<Customer> findByGroup(String group) {
//
//        return adminService.findByGroup(group);
//    }
//
//
//    public ArrayList<Customer> findByDepartmentAndCourse(Long id, int course) {
//        return adminService.findByDepartmentAndCourse(id, course);
//    }
}
