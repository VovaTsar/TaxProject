package com.mytask.service;

import com.mytask.domain.Student;

import java.util.ArrayList;


public interface StudentService {
    Student register(Student student);

    Student findById(Long id);

    Student login(String email, String password);

    Student deleteById(Long id);

//    ArrayList<Student> findByDepartment(Long id);
//
//    ArrayList<Student> findByYear(int year);
//
//    ArrayList<Student> findByGroup(String group);
//
//    ArrayList<Student> findByDepartmentAndCourse(Long id, int course);

    ArrayList<Student> findAll();

    void update(Student student);

}
