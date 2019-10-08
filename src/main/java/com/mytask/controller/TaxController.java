package com.mytask.controller;

import com.mytask.domain.order.Tax;
import com.mytask.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TaxController {

    private TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    public Tax save(Tax tax) {
        return taxService.save(tax);
    }

    public ArrayList<Tax> findAll() {

        return taxService.findAll();
    }

    public Tax findById(Long id) {

        return taxService.findById(id);
    }

    public void update(Tax tax) {

        taxService.update(tax);
    }

    public Tax deleteById(Long id) {

        return taxService.deleteById(id);
    }

}
