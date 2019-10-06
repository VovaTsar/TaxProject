package com.mytask.repository;

import com.mytask.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {


    private Map<Long, Customer> idToCustomers = new HashMap<>();
    private static Long counter = 0L;

    @Override
    public Customer save(Customer customer) {

        return idToCustomers.put(++counter, customer);
    }


    @Override
    public Optional<Customer> findById(Long id) {

        return Optional.ofNullable(idToCustomers.get(id));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        Customer customer =null;
        for (Long i = 1L; i <idToCustomers.size()+1 ; i++) {
            if (idToCustomers.get(i).getEmail().equals(email)){
                customer =idToCustomers.get(i);
                break;
            }

        }
        return Optional.ofNullable(customer);
    }

    @Override
    public ArrayList<Customer> findAll() {
        return new ArrayList<>(idToCustomers.values());
    }


    @Override
    public void update(Customer customer) {
        idToCustomers.replace(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> deleteById(Long id) {

        return Optional.ofNullable(idToCustomers.remove(id));
    }

//    @Override
//    public ArrayList<Customer> findByDepartment(Long id) {
//        ArrayList<Customer> findByFacultyStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (id.equals(idToStudents.get(i).getDepartment().getId())) {
//                findByFacultyStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByFacultyStudents;
//    }
//
//
//    @Override
//    public ArrayList<Customer> findByYear(int year) {
//        ArrayList<Customer> findByYearStudents = new ArrayList<>();
//
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (year < idToStudents.get(i).getBirthday().getYear()) {
//                findByYearStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByYearStudents;
//    }
//
//
//    @Override
//    public ArrayList<Customer> findByGroup(String group) {
//        ArrayList<Customer> findByGroupStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (group.equals(idToStudents.get(i).getGroup())) {
//                findByGroupStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByGroupStudents;
//    }
//
//    @Override
//    public ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course) {
//        ArrayList<Customer> findByDepartmentAndCourseStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (idDepartment.equals(idToStudents.get(i).getDepartment().getId())
//                    && course == idToStudents.get(i).getCourse()) {
//                findByDepartmentAndCourseStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByDepartmentAndCourseStudents;
//    }

}
