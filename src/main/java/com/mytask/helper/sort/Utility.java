package com.mytask.helper.sort;


import com.mytask.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
@Component
final class Utility {
    private Utility() {
    }

    public static void swap(ArrayList<Customer> customers, int i, int j) {
        Collections.swap(customers, i, j);
    }
}
