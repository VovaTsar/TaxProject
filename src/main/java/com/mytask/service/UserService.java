package com.mytask.service;

import com.mytask.domain.customer.Customer;
import com.mytask.domain.order.Tax;

import java.util.ArrayList;


public interface UserService {
    Customer register(Customer customer);

    Customer findById(Long id);

    Customer login(String email, String password);

    void update(Customer customer);

    ArrayList<Tax> findAllTaxes(Customer customer);

    void addTax(Customer customer, Long idTax);

    void deleteTax(Customer customer, Long idTax);

    ArrayList<Tax> sortTax(Customer customer);

    int sumOfTaxes(Customer customer);

}
