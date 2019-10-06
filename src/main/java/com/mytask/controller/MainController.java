package com.mytask.controller;

import com.mytask.domain.Student;

import com.mytask.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class MainController {
    private StudentService studentService;

    @Autowired
    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

    public Optional<Student> register(Student student) {
        return studentService.register(student);
    }


    public Optional<Student> findById(Long id) {

        return studentService.findById(id);
    }

    public Optional<Student> login(String email, String password) {
        return studentService.login(email, password);
    }

    public void update(Student student) {

        studentService.update(student);
    }

    public ArrayList<Student> findAll() {

        return studentService.findAll();
    }

    public Optional<Student> deleteById(Long id) {

        return studentService.deleteById(id);
    }


    public ArrayList<Student> findByDepartment(Long id) {

        return studentService.findByDepartment(id);
    }


    public ArrayList<Student> findByYear(int year) {

        return studentService.findByYear(year);
    }


    public ArrayList<Student> findByGroup(String group) {

        return studentService.findByGroup(group);
    }


    public ArrayList<Student> findByDepartmentAndCourse(Long id, int course) {
        return studentService.findByDepartmentAndCourse(id, course);
    }
}
