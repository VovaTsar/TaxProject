package com.mytask.service.impl;

import com.mytask.domain.customer.Customer;
import com.mytask.domain.order.Tax;
import com.mytask.exeption.CustomerNotExistRuntimeException;
import com.mytask.exeption.LoginRuntimeException;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.exeption.UserNotValidateRuntimeException;
import com.mytask.repository.CustomerRepository;
import com.mytask.service.TaxService;
import com.mytask.service.UserService;
import com.mytask.util.encoder.PasswordEncoder;
import com.mytask.util.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;
    private TaxService taxService;
    private UserValidator userValidator;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository, TaxService taxService, UserValidator userValidator) {
        this.customerRepository = customerRepository;
        this.taxService = taxService;
        this.userValidator = userValidator;
    }

    @Override
    public Customer register(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }
        String encodePassword = PasswordEncoder.generateSecurePassword(customer.getPassword());
        Customer customerWithEncode = (Customer) customer.clone(encodePassword);
        return customerRepository.save(customerWithEncode);
    }

    @Override
    public Customer login(String email, String password) {
        String encodePassword = PasswordEncoder.generateSecurePassword(password);

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
        return userFindById.orElseThrow(() -> new UncorrectedIdRuntimeException("Id must be correct"));
    }


    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }

        customerRepository.update(customer);
    }

    @Override
    public ArrayList<Tax> findAllTaxes(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }
        return customer.getReport().getTaxes();
    }

    @Override
    public void addTax(Customer customer, Long idTax) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer hasn't validate data");
        }
        Tax tax = taxService.findById(idTax);
        customer.getReport().add(tax);
        update(customer);
    }

    @Override
    public void deleteTax(Customer customer, Long idTax) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }
        Tax tax = taxService.findById(idTax);
        customer.getReport().remove(tax);
        update(customer);
    }

    @Override
    public ArrayList<Tax> sortTax(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }

        return customer.getReport().sortByAmountTaxes();
    }

    @Override
    public int sumOfTaxes(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if (!userValidator.validate(customer)) {
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }
        return customer.getReport().sumOfTaxes();
    }


}
