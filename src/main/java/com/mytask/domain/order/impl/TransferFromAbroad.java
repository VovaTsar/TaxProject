package com.mytask.domain.order.impl;

import com.mytask.domain.order.Tax;

public class TransferFromAbroad extends Tax {
    public TransferFromAbroad(int amountOfTaxes, Currency currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "TransferFromAbroad{id: " + id + " amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
