package com.mytask.domain.order;

import com.mytask.exeption.TaxActionWithNullRuntimeException;

import java.util.ArrayList;
import java.util.Collections;

public class Report {
    private ArrayList<Tax> taxes;


    public Report() {
        taxes = new ArrayList<>();
    }

    public Report(ArrayList<Tax> taxes) {
        this.taxes = taxes;
    }

    public ArrayList<Tax> getTaxes() {
        return taxes;
    }

    public void add(Tax tax) {
        if (tax == null) {
            throw new TaxActionWithNullRuntimeException("Add null to Tax");
        }
        taxes.add(tax);
    }

    public void remove(Tax tax) {
        if (tax == null) {
            throw new TaxActionWithNullRuntimeException("Delete null to Tax");
        }
        taxes.remove(tax);
    }

    public int sumOfTaxes() {
        int sum = 0;
        for (Tax tax : taxes) {
            sum += tax.getAmountOfTaxes();
        }
        return sum;
    }

    public ArrayList<Tax> sortByAmountTaxes() {
        Collections.sort(taxes);
        return taxes;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Report {");
        for (Tax tax : taxes) {
            result.append(tax.toString());
        }
        result.append("}");
        return result.toString();
    }
}
