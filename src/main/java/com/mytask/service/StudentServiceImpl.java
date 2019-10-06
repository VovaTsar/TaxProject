package com.mytask.service;


import com.mytask.domain.Student;
import com.mytask.exeption.LoginRuntimeException;

import com.mytask.exeption.UncorrectedIdRuntimeException;
import com.mytask.exeption.UserNotExistRuntimeException;
import com.mytask.helper.utillity.PasswordUtils;
import com.mytask.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student register(Student student) {
        if (student == null) {
            throw new UserNotExistRuntimeException("User is not exist");
        }
        String encodePassword = PasswordUtils.generateSecurePassword(student.getPassword());
        Student studentWithEncode = (Student) student.clone(encodePassword);
        return studentRepository.save(studentWithEncode);
    }

    @Override
    public Student findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }
        Optional<Student> userFindById = studentRepository.findById(id);
        if (userFindById.isPresent()){
            return userFindById.get();
        }
        throw new UncorrectedIdRuntimeException("Id must be correct");
    }

    @Override
    public ArrayList<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            throw new UserNotExistRuntimeException("User is not exist");
        }
        studentRepository.update(student);
    }

    @Override
    public Student login(String email, String password) {
        String encodePassword = PasswordUtils.generateSecurePassword(password);

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new LoginRuntimeException("Login are not exist"));
        String userPassword = student.getPassword();
        if (userPassword.equals(encodePassword)) {
            return student;
        }
        throw new LoginRuntimeException("Password is not correct");
    }

    @Override
    public Student deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }
        Optional<Student> studentDeleteById = studentRepository.deleteById(id);
        if (studentDeleteById.isPresent()){
            return studentDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id must be correct");
    }

//    @Override
//    public ArrayList<Student> findByDepartment(Long id) {
//        if (id < 0) {
//            throw new IllegalArgumentException("id must be > 0");
//        }
//        return studentRepository.findByDepartment(id);
//    }
//
//    @Override
//    public ArrayList<Student> findByYear(int year) {
//        if (year < 1920) {
//            throw new IllegalArgumentException("id must be > 0");
//        }
//        return studentRepository.findByYear(year);
//    }
//
//    @Override
//    public ArrayList<Student> findByGroup(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Group is null");
//        }
//        return studentRepository.findByGroup(group);
//    }
//
//    @Override
//    public ArrayList<Student> findByDepartmentAndCourse(Long id, int course) {
//        if (id < 0 || course > 6 || course < 0) {
//            throw new IllegalArgumentException("Course must be in range 1 to 6 and Id must be >0");
//        }
//        return studentRepository.findByDepartmentAndCourse(id, course);
//    }
}
