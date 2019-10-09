package com.mytask.service.impl;

import com.mytask.domain.customer.Customer;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.repository.CustomerRepository;
import com.mytask.service.AdminService;
import com.mytask.service.TaxService;
import com.mytask.util.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

    @Autowired
    public AdminServiceImpl(CustomerRepository customerRepository, TaxService taxService, UserValidator userValidator) {
        super(customerRepository, taxService, userValidator);
    }

    @Override
    public ArrayList<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }
        Optional<Customer> studentDeleteById = customerRepository.deleteById(id);
        return studentDeleteById.orElseThrow(() -> new UncorrectedIdRuntimeException("Id must be correct"));
    }
}
