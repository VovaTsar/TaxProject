package com.mytask.domain.order.impl;

import com.mytask.domain.order.Tax;

public class ReceiptAsGift extends Tax {


    public ReceiptAsGift(int amountOfTaxes, Currency currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "ReceiptAsGift{id: " + id + " amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
