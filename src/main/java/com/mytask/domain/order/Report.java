package com.mytask.domain.order;

import com.mytask.exeption.TaxActionWithNullRuntimeExeption;

import java.util.ArrayList;
import java.util.Collections;

public class Report {
    private ArrayList<Taxes> taxes;

    public Report() {
        taxes = new ArrayList<>();
    }

    public Report(ArrayList<Taxes> taxes) {
        this.taxes = taxes;
    }

    public void add(Taxes tax) {
        if (tax == null) {
            throw new TaxActionWithNullRuntimeExeption("Add null to Taxes");
        }
        taxes.add(tax);
    }

    public void remove(Taxes tax) {
        if (tax == null) {
            throw new TaxActionWithNullRuntimeExeption("Delete null to Taxes");
        }
        taxes.remove(tax);
    }

    public int sumOfTaxes() {
        int sum = 0;
        for (Taxes tax : taxes) {
            sum += tax.getAmountOfTaxes();
        }
        return sum;
    }

    public ArrayList<Taxes> sortByAmountTaxes() {
        Collections.sort(taxes);
        return taxes;
    }

}
