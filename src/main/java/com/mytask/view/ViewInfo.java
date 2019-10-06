package com.mytask.view;

import com.mytask.controller.MainController;
import com.mytask.domain.Department;
import com.mytask.domain.Student;
import com.mytask.helper.utillity.Converter;
import com.mytask.helper.sort.BubbleSort;
import com.mytask.helper.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ViewInfo {

    private MainController mainController;
    private ResourceBundle lang;

    @Autowired
    public ViewInfo(MainController mainController) {
        this.mainController = mainController;
    }

    private Scanner in = new Scanner(System.in);

    public void run() {
        chooseMenuLang();
    }


    public void chooseMenuLang() {
        System.out.println("Choose language/Оберіть мову");
        System.out.println("English (1)");
        System.out.println("Українська (2)");
        int chooseLanguage = in.nextInt();

        chooseLang(chooseLanguage);
    }

    void chooseLang(int chooseLang) {

        try {
            if (chooseLang == 1) {
                lang = ResourceBundle.getBundle("resources", new Locale("en"), new Converter());
            } else if (chooseLang == 2) {
                lang = ResourceBundle.getBundle("resources", new Locale("ua"), new Converter());
            } else
                chooseMenuLang();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }
        menu();
    }

    void menu() {

        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("viewStudent"));
        System.out.println("2 - " + lang.getString("addStudent"));
        System.out.println("3 - " + lang.getString("sortStudent"));
        System.out.println("4 - " + lang.getString("loginStudent"));
        System.out.println("5 - " + lang.getString("inputId"));
        System.out.println("6 - " + lang.getString("inputIdDepartment"));
        System.out.println("7 - " + lang.getString("inputGroup"));
        System.out.println("8 - " + lang.getString("inputCourse"));
        System.out.println("9 - " + lang.getString("chooseLanguage"));

        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                printAllStudents(BubbleSort.sort(mainController.findAll()));
                break;

            case 2:
                createStudentFromConsole();
                break;
            case 3:
                sortStudent();
                break;
            case 4:
                System.out.println(login());
                break;
            case 5:
                System.out.println(findById());
                break;
            case 6:
                printAllStudents(findByDepartment());
                break;
            case 7:
                printAllStudents(findByGroup());
                break;
            case 8:
                printAllStudents(findByDepartmentAndCourse());
                break;
            case 9:
                chooseMenuLang();
                break;
        }
        menu();
    }

    void printAllStudents(ArrayList<Student> students) {
        if (mainController.findAll().isEmpty())
            System.out.println(lang.getString("noStudentYet"));
        else {
            System.out.println("\n" + lang.getString("listStudent"));
            for (Student student : students
            ) {
                System.out.println(student);
            }
            System.out.println();
        }
    }

    void createStudentFromConsole() {

        String name = writeFieldValidator("name");
        String surname = writeFieldValidator("surname");
         String email = writeFieldValidator("email");
        //System.out.println(lang.getString("emailStudent"));
      //  String email = in.nextLine();
        String phoneNumber = writeFieldValidator("phoneNumber");
        String birthday = writeFieldValidator("date");
        Department department = new Department(1L, "dep1");
        System.out.println(lang.getString("groupStudent"));
        String group = in.nextLine();
        int course = Integer.parseInt(writeFieldValidator("course"));
        System.out.println(lang.getString("passwordStudent"));
        String password = in.nextLine();

//        javax.validation.ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();

        Student student = Student.builder()
                .withName(name)
                .withSurname(surname)
                .withBirthday(splitBirthday(birthday))
                .withDepartment(department)
                .withPhoneNumber(phoneNumber)
                .withGroup(group)
                .withPassword(password)
                .withCourse(course)
                .withEmail(email)
                .build();
        mainController.register(student);
        System.out.println(lang.getString("studentCreated") + "\n");

        menu();
//
//        Set<ConstraintViolation<Student>> constraintViolations = validator.validate(student);
//
//        if (constraintViolations.size() > 0) {
//            for (ConstraintViolation<Student> violation : constraintViolations) {
//                System.out.println(violation.getMessage());
//            }
//        } else {
//            System.out.println("Valid Object");
//        }
    }
    LocalDate splitBirthday(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthday, formatter);
    }

    void sortStudent() {
        System.out.println(lang.getString("usersAreSorted") + "\n");
        printAllStudents(BubbleSort.sort(mainController.findAll()));
    }

    private String writeFieldValidator(String nameField) {

        String key = nameField + "Student";
        System.out.println(lang.getString(key));
        String fieldInput = in.nextLine();
        if (!ValidatorFactory.getValidator(nameField).validate(fieldInput)) {
            System.out.println(lang.getString("uncorrectedValue"));
            fieldInput = writeFieldValidator(nameField);
        }
        return fieldInput;
    }

    private Optional<Student> login(){
        System.out.println("");
        String email = writeFieldValidator("email");
        System.out.println(lang.getString("passwordStudent"));
        String password = in.nextLine();
        return mainController.login(email,password);
    }
    public void print(ArrayList<Student> students) {
        for (Student student : students) {
            print(student);
        }
    }

    public void print(Student students) {
        System.out.println(students);
    }

    private Optional<Student> findById() {
        System.out.println(lang.getString("inputId"));
        return mainController.findById(in.nextLong());
    }

    private ArrayList<Student> findByDepartment() {
        System.out.println(lang.getString("inputIdDepartment"));
        return mainController.findByDepartment(in.nextLong());
    }

    private ArrayList<Student> findByGroup() {
        System.out.println(lang.getString("inputGroup"));
        String group = in.nextLine();
        group = in.nextLine();
        return mainController.findByGroup(group);
    }

    private ArrayList<Student> findByDepartmentAndCourse() {
        System.out.println(lang.getString("inputIdDepartment"));
        Long department = in.nextLong();
        System.out.println(lang.getString("inputCourse"));
        int course = in.nextInt();
        return mainController.findByDepartmentAndCourse(department, course);
    }

}
