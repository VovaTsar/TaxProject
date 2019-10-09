package com.mytask.service;

import com.mytask.domain.customer.Customer;
import com.mytask.domain.order.Tax;
import com.mytask.exeption.*;
import com.mytask.helper.utillity.PasswordUtils;
import com.mytask.helper.validator.impl.UserValidator;
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
    private TaxService taxService;
    private UserValidator userValidator;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository, TaxService taxService, UserValidator userValidator) {
        this.customerRepository = customerRepository;
        this.taxService = taxService;
        this.userValidator=userValidator;
    }

    @Override
    public Customer register(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if(!userValidator.validate(customer)){
            throw new UserNotValidateRuntimeException("Customer has not validate data");
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
        if(!userValidator.validate(customer)){
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }

        customerRepository.update(customer);
    }

    @Override
    public ArrayList<Tax> findAllTaxes(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if(!userValidator.validate(customer)){
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }
        return customer.getReport().getTaxes();
    }

    @Override
    public void addTax(Customer customer, Long idTax) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if(!userValidator.validate(customer)){
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
        if(!userValidator.validate(customer)){
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
        if(!userValidator.validate(customer)){
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }

        return customer.getReport().sortByAmountTaxes();
    }

    @Override
    public int sumOfTaxes(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("Customer is not exist");
        }
        if(!userValidator.validate(customer)){
            throw new UserNotValidateRuntimeException("Customer has not validate data");
        }
        return customer.getReport().sumOfTaxes();
    }


}
