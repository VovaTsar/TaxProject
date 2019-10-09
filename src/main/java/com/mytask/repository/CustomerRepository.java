package com.mytask.repository;

import com.mytask.domain.customer.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}
