package com.mytask.domain.order;

import com.mytask.domain.order.impl.AdditionalWork;
import com.mytask.domain.order.impl.Currency;
import com.mytask.domain.order.impl.TransferFromAbroad;
import com.mytask.exeption.TaxActionWithNullRuntimeException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ReportTest {
    private Report report;
    private AdditionalWork additionalWork;
    private TransferFromAbroad transferFromAbroad;

    @Before
    public void setUp() {
        report = new Report();
        additionalWork = new AdditionalWork(4000, Currency.PRIVILEGE,"TS");
        transferFromAbroad = new TransferFromAbroad(3000, Currency.PRIVILEGE,"TS");
        report.add(additionalWork);
        report.add(transferFromAbroad);
    }

    @Test
    public void add() {
        AdditionalWork additionalWork1 = new AdditionalWork(2000, Currency.PRIVILEGE,"TS");
        report.add(additionalWork1);
        assertThat(report.getTaxes(), hasItem(additionalWork1));
    }

    @Test
    public void remove() {
        AdditionalWork additionalWork1 = new AdditionalWork(2000, Currency.PRIVILEGE,"TS");
        report.add(additionalWork1);
        report.remove(additionalWork1);
        assertThat(report.getTaxes(), not(additionalWork1));
    }

    @Test
    public void sort() {
        AdditionalWork additionalWork1 = new AdditionalWork(2000, Currency.PRIVILEGE,"TS");
        report.add(additionalWork1);

        ArrayList<Tax> taxes = new ArrayList<>();
        taxes.add(additionalWork1);
        taxes.add(transferFromAbroad);
        taxes.add(additionalWork);

        assertThat(taxes, is(report.sortByAmountTaxes()));
    }

    @Test
    public void getSummaryOfTaxes() {
        AdditionalWork additionalWork1 = new AdditionalWork(2000, Currency.PRIVILEGE,"TS");
        report.add(additionalWork1);
        assertThat(9000, is(report.sumOfTaxes()));
    }



    @Test(expected = TaxActionWithNullRuntimeException.class)
    public void shouldReturnActionTaxesWithNullRuntimeException() {
        report.add(null);
    }
}