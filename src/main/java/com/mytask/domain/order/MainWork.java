package com.mytask.domain.order;

public class MainWork extends Taxes {
    public MainWork(int amountOfTaxes, String currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "MainWork{" + "amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
