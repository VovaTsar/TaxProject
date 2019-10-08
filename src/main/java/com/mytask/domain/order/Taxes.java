package com.mytask.domain.order;



import com.mytask.exeption.TaxesUncorrectedDataRuntimeException;

import java.util.Objects;

public abstract class Taxes implements Comparable<Taxes> {
    protected final int amountOfTaxes;
    protected final Currency currency;
    protected final String taxRecipient;


    public Taxes(int amountOfTaxes, Currency currency, String taxRecipient) {
        if (amountOfTaxes<0||taxRecipient==null){
            throw new TaxesUncorrectedDataRuntimeException("Amount of taxes must be positive and tax recipient must be not null");
        }
        this.amountOfTaxes = amountOfTaxes;
        this.currency = currency;
        this.taxRecipient = taxRecipient;
    }

    public int getAmountOfTaxes() {
        return amountOfTaxes;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getTaxRecipient() {
        return taxRecipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Taxes taxes = (Taxes) o;
        return amountOfTaxes == taxes.amountOfTaxes &&
                currency == taxes.currency &&
                Objects.equals(taxRecipient, taxes.taxRecipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfTaxes, currency, taxRecipient);
    }

    public abstract  String toString();

    @Override
    public int compareTo(Taxes o) {
        return this.amountOfTaxes-o.getAmountOfTaxes();
    }
}
