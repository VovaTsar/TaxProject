package com.mytask.service;

import com.mytask.domain.customer.Customer;
import com.mytask.exeption.LoginRuntimeException;
import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.exeption.UserNotExistRuntimeException;
import com.mytask.helper.utillity.PasswordUtils;
import com.mytask.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
        if (userFindById.isPresent()) {
            return userFindById.get();
        }
        throw new UncorrectedIdRuntimeException("Id must be correct");
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

}
