package com.mytask.domain.order;

public class SaleOfProperty extends Taxes {
    public SaleOfProperty(int amountOfTaxes, String currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "SaleOfProperty{" + "amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
