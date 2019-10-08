package com.mytask.service;

import com.mytask.domain.Customer;


public interface UserService {
    Customer register(Customer customer);

    Customer findById(Long id);

    Customer login(String email, String password);


//    ArrayList<Customer> findByDepartment(Long id);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long id, int course);

    void update(Customer customer);

}
