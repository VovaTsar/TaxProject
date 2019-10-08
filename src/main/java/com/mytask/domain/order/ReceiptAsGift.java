package com.mytask.domain.order;

public class ReceiptAsGift extends Taxes {
    public ReceiptAsGift(int amountOfTaxes, String currency, String taxRecipient) {
        super(amountOfTaxes, currency, taxRecipient);
    }

    @Override
    public String toString() {
        return "ReceiptAsGift{" + "amountOfTaxes=" + amountOfTaxes + ", currency=" + currency + ", taxRecipient=" + taxRecipient + '}';
    }
}
