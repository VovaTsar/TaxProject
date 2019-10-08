package com.mytask.controller;

import com.mytask.domain.customer.Customer;
import com.mytask.domain.order.Tax;
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

    public Customer login(String email, String password) {
        return adminService.login(email, password);
    }

    public Customer findById(Long id) {
        return adminService.findById(id);
    }

    public ArrayList<Customer> findAll() {
        return adminService.findAll();
    }

    public void update(Customer customer) {
        adminService.update(customer);
    }

    public Customer deleteById(Long id) {
        return adminService.deleteById(id);
    }

    void addTax(Customer customer, Tax tax) {
        adminService.addTax(customer, tax);
    }

    void deleteTax(Customer customer, Tax tax) {
        adminService.deleteTax(customer, tax);
    }

    ArrayList<Tax> sortTax(Customer customer) {
        return adminService.sortTax(customer);
    }

    int sumOfTaxes(Customer customer) {
        return adminService.sumOfTaxes(customer);
    }

}
