package com.mytask.service;

import com.mytask.domain.Customer;

import java.util.ArrayList;

public interface AdminService extends UserService{
    ArrayList<Customer> findAll();

    Customer deleteById(Long id);
}
