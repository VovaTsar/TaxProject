package com.mytask.service;

import com.mytask.domain.customer.Customer;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

    @Autowired
    public AdminServiceImpl(CustomerRepository customerRepository,TaxService taxService) {
        super(customerRepository,taxService);
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
        if (studentDeleteById.isPresent()) {
            return studentDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id must be correct");
    }
}
