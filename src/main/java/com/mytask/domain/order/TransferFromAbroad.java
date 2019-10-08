package com.mytask.domain.order;

public class TransferFromAbroad extends Taxes {
    public TransferFromAbroad(int amountOfTaxes, String currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "TransferFromAbroad{" + "amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
