package com.mytask.repository;

import com.mytask.domain.customer.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {


    Optional<Customer> findByEmail(String email);


//    ArrayList<Customer> findByDepartment(Long id);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long id, int course);


}
