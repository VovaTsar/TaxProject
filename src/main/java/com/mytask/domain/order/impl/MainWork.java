package com.mytask.domain.order.impl;

import com.mytask.domain.order.Tax;

public class MainWork extends Tax {


    public MainWork(int amountOfTaxes, Currency currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "MainWork{ id: " + id + " amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
