package com.mytask.repository;

import com.mytask.domain.Customer;


import java.util.ArrayList;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);


    Optional<Customer> findById(Long id);

    Optional<Customer> findByEmail(String email);

    ArrayList<Customer> findAll();

    void update(Customer customer);


    Optional<Customer> deleteById(Long id);


//    ArrayList<Customer> findByDepartment(Long id);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long id, int course);


}
