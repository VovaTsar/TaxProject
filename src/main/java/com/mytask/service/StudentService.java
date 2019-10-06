package com.mytask.service;


import com.mytask.domain.Student;

import java.util.ArrayList;
import java.util.Optional;

public interface StudentService {
    Optional<Student> register(Student student);

    Optional<Student> findById(Long id);

    ArrayList<Student> findAll();

    void update(Student student);
    Optional<Student> login(String email,String password);
    Optional<Student> deleteById(Long id);

    ArrayList<Student> findByDepartment(Long id);

    ArrayList<Student> findByYear(int year);

    ArrayList<Student> findByGroup(String group);

    ArrayList<Student> findByDepartmentAndCourse(Long id, int course);


}
