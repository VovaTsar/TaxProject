package com.mytask.service;

import com.mytask.domain.customer.Customer;


public interface UserService {
    Customer register(Customer customer);

    Customer findById(Long id);

    Customer login(String email, String password);

    void update(Customer customer);

}
