package com.mytask.init;



import com.mytask.domain.Address;
import com.mytask.domain.Department;
import com.mytask.domain.Student;
import com.mytask.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Menu {

    private StudentService studentService;

    @Autowired
    public Menu(StudentService studentService) {
        this.studentService = studentService;
    }


    public void run() {
        Department department = new Department(7L, "kpi");
        Student vova = Student.builder()
                .withName("Vova")
                .withSurname("Ts")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withGroup("IP-64")
                .withPassword("12345")
                .withAddress(new Address("Kyiv", "WWW", 7))
                .withPhoneNumber("3807341345")
                .withDepartment(department)
                .withEmail("vova@gmail.com")
                .withCourse(4)
                .build();

        Student vania = Student.builder()
                .withName("Vania")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withGroup("IP-64")
                .withPassword("12345")
                .withAddress(new Address("Kyiv", "WWW", 8))
                .withPhoneNumber("38043545345")
                .withDepartment(department)
                .withCourse(4)
                .withEmail("vania@gmail.com")
                .build();

        Student vasyl = Student.builder()
                .withName("Vasyl")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withGroup("IP-64")
                .withPassword("12345")
                .withAddress(new Address("Kyiv", "WWW", 8))
                .withPhoneNumber("38063355345")
                .withDepartment(department)
                .withEmail("vasyl@gmail.com")
                .withCourse(4)
                .build();
        studentService.register(vova);
        studentService.register(vasyl);
        studentService.register(vania);

    }
}
