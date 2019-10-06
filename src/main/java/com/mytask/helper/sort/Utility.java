package com.mytask.helper.sort;


import com.mytask.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
@Component
final class Utility {
    private Utility() {
    }

    public static void swap(ArrayList<Student> students, int i, int j) {
        Collections.swap(students, i, j);
    }
}
