package com.mytask.service;


import com.mytask.domain.Customer;
import com.mytask.exeption.LoginRuntimeException;

import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.exeption.UserNotExistRuntimeException;
import com.mytask.helper.utillity.PasswordUtils;
import com.mytask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(Customer customer) {
        if (customer == null) {
            throw new UserNotExistRuntimeException("User is not exist");
        }
        String encodePassword = PasswordUtils.generateSecurePassword(customer.getPassword());
        Customer customerWithEncode = (Customer) customer.clone(encodePassword);
        return customerRepository.save(customerWithEncode);
    }

    @Override
    public Customer findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }
        Optional<Customer> userFindById = customerRepository.findById(id);
        if (userFindById.isPresent()){
            return userFindById.get();
        }
        throw new UncorrectedIdRuntimeException("Id must be correct");
    }

    @Override
    public ArrayList<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new UserNotExistRuntimeException("User is not exist");
        }
        customerRepository.update(customer);
    }

    @Override
    public Customer login(String email, String password) {
        String encodePassword = PasswordUtils.generateSecurePassword(password);

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new LoginRuntimeException("Login are not exist"));
        String userPassword = customer.getPassword();
        if (userPassword.equals(encodePassword)) {
            return customer;
        }
        throw new LoginRuntimeException("Password is not correct");
    }

    @Override
    public Customer deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }
        Optional<Customer> studentDeleteById = customerRepository.deleteById(id);
        if (studentDeleteById.isPresent()){
            return studentDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id must be correct");
    }

//    @Override
//    public ArrayList<Customer> findByDepartment(Long id) {
//        if (id < 0) {
//            throw new IllegalArgumentException("id must be > 0");
//        }
//        return customerRepository.findByDepartment(id);
//    }
//
//    @Override
//    public ArrayList<Customer> findByYear(int year) {
//        if (year < 1920) {
//            throw new IllegalArgumentException("id must be > 0");
//        }
//        return customerRepository.findByYear(year);
//    }
//
//    @Override
//    public ArrayList<Customer> findByGroup(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Group is null");
//        }
//        return customerRepository.findByGroup(group);
//    }
//
//    @Override
//    public ArrayList<Customer> findByDepartmentAndCourse(Long id, int course) {
//        if (id < 0 || course > 6 || course < 0) {
//            throw new IllegalArgumentException("Course must be in range 1 to 6 and Id must be >0");
//        }
//        return customerRepository.findByDepartmentAndCourse(id, course);
//    }
}
