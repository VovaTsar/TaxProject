package com.mytask.domain.order;

public class AdditionalWork extends Taxes{
    public AdditionalWork(int amountOfTaxes, String currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "AdditionalWork{" + "amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
