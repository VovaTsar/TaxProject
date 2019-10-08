package com.mytask.domain.customer;

public interface CustomerPrototype {
    CustomerPrototype clone(String newPassword);
}
