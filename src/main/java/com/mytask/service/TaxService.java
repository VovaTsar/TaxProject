package com.mytask.service;

import com.mytask.domain.order.Tax;

import java.util.ArrayList;

public interface TaxService {
    Tax save(Tax tax);

    Tax findById(Long id);

    void update(Tax tax);

    ArrayList<Tax> findAll();

    Tax deleteById(Long id);
}
