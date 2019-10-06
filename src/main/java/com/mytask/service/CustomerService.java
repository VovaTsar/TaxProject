package com.mytask.service;

import com.mytask.domain.Customer;

import java.util.ArrayList;


public interface CustomerService {
    Customer register(Customer customer);

    Customer findById(Long id);

    Customer login(String email, String password);

    Customer deleteById(Long id);

//    ArrayList<Customer> findByDepartment(Long id);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long id, int course);

    ArrayList<Customer> findAll();

    void update(Customer customer);

}
