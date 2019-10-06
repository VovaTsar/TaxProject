package com.mytask.domain;

public interface CustomerPrototype {
    CustomerPrototype clone(String newPassword);
}
