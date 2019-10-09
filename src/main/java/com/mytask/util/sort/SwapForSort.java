package com.mytask.util.sort;


import com.mytask.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
final class SwapForSort {
    private SwapForSort() {
    }

    public static void swap(ArrayList<Customer> customers, int i, int j) {
        Collections.swap(customers, i, j);
    }
}
