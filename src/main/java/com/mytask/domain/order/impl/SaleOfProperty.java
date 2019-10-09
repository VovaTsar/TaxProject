package com.mytask.domain.order.impl;

import com.mytask.domain.order.Tax;

public class SaleOfProperty extends Tax {
    public SaleOfProperty(int amountOfTaxes, Currency currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "SaleOfProperty{id: " + id + " amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
