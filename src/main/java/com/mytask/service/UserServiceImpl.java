package com.mytask.service;

import com.mytask.domain.customer.Customer;
import com.mytask.domain.order.Tax;
import com.mytask.exeption.LoginRuntimeException;
import com.mytask.exeption.TaxNotExistRuntimeException;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.exeption.CustomerNotExistRuntimeException;
import com.mytask.helper.utillity.PasswordUtils;
import com.mytask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        String encodePassword = PasswordUtils.generateSecurePassword(customer.getPassword());
        Customer customerWithEncode = (Customer) customer.clone(encodePassword);
        return customerRepository.save(customerWithEncode);
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
    public Customer findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }
        Optional<Customer> userFindById = customerRepository.findById(id);
        if (userFindById.isPresent()) {
            return userFindById.get();
        }

        throw new UncorrectedIdRuntimeException("Id must be correct");
    }


    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }

        customerRepository.update(customer);
    }

    @Override
    public void addTax(Customer customer, Tax tax) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (tax == null) {
            throw new TaxNotExistRuntimeException(" Tax is not exist");
        }
        customer.getReport().add(tax);
        update(customer);
    }

    @Override
    public void deleteTax(Customer customer, Tax tax) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (tax == null) {
            throw new TaxNotExistRuntimeException(" Tax is not exist");
        }
        customer.getReport().remove(tax);
        update(customer);
    }

    @Override
    public ArrayList<Tax> sortTax(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }

        return customer.getReport().sortByAmountTaxes();
    }

    @Override
    public int sumOfTaxes(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }

        return customer.getReport().sumOfTaxes();
    }


}
