package com.mytask.domain.order;

public class TransferFromAbroad extends Tax {
    public TransferFromAbroad(int amountOfTaxes, Currency currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "TransferFromAbroad{id: " + id + " amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
