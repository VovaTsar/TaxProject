package com.mytask.domain.order;


import com.mytask.domain.order.impl.Currency;
import com.mytask.exeption.TaxUncorrectedDataRuntimeException;

import java.util.Objects;

public abstract class Tax implements Comparable<Tax> {
    protected final Long id;
    protected final int amountOfTaxes;
    protected final Currency currency;
    protected final String taxRecipient;
    protected static Long counter = 0L;


    public Tax(int amountOfTaxes, Currency currency, String taxRecipient) {
        if (amountOfTaxes < 0 || taxRecipient == null) {
            throw new TaxUncorrectedDataRuntimeException("Amount of taxes must be positive and tax recipient must be not null");
        }
        this.id = ++counter;
        this.amountOfTaxes = amountOfTaxes;
        this.currency = currency;
        this.taxRecipient = taxRecipient;

    }

    public Long getId() {
        return id;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tax tax = (Tax) o;
        return amountOfTaxes == tax.amountOfTaxes &&
                currency == tax.currency &&
                Objects.equals(taxRecipient, tax.taxRecipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountOfTaxes, currency, taxRecipient);
    }

    public abstract String toString();

    @Override
    public int compareTo(Tax o) {
        return this.amountOfTaxes - o.getAmountOfTaxes();
    }
}
