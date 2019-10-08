package com.mytask.view;

import com.mytask.controller.AdminController;
import com.mytask.domain.Customer;
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

    private AdminController adminController;
    private ResourceBundle lang;

    @Autowired
    public ViewInfo(AdminController adminController) {
        this.adminController = adminController;
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
        System.out.println("1 - " + lang.getString("viewCustomer"));
        System.out.println("2 - " + lang.getString("addCustomer"));
        System.out.println("3 - " + lang.getString("sortCustomer"));
        System.out.println("4 - " + lang.getString("loginCustomer"));
        System.out.println("5 - " + lang.getString("inputId"));
//        System.out.println("6 - " + lang.getString("inputIdDepartment"));
//        System.out.println("7 - " + lang.getString("inputGroup"));
//        System.out.println("8 - " + lang.getString("inputCourse"));
        System.out.println("9 - " + lang.getString("chooseLanguage"));

        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                printAllCustomers(BubbleSort.sort(adminController.findAll()));
                break;

            case 2:
                createCustomerFromConsole();
                break;
            case 3:
                sortCustomer();
                break;
            case 4:
                System.out.println(login());
                break;
            case 5:
                System.out.println(findById());
                break;
//            case 6:
//                printAllCustomers(findByDepartment());
//                break;
//            case 7:
//                printAllCustomers(findByGroup());
//                break;
//            case 8:
//                printAllCustomers(findByDepartmentAndCourse());
//                break;
            case 9:
                chooseMenuLang();
                break;
        }
        menu();
    }

    void printAllCustomers(ArrayList<Customer> customers) {
        if (adminController.findAll().isEmpty())
            System.out.println(lang.getString("noCustomerYet"));
        else {
            System.out.println("\n" + lang.getString("listCustomer"));
            for (Customer customer : customers
            ) {
                System.out.println(customer);
            }
            System.out.println();
        }
    }

    void createCustomerFromConsole() {

        String name = writeFieldValidator("name");
        String surname = writeFieldValidator("surname");
         String email = writeFieldValidator("email");
        //System.out.println(lang.getString("emailCustomer"));
      //  String email = in.nextLine();
        String phoneNumber = writeFieldValidator("phoneNumber");
        String birthday = writeFieldValidator("date");
    //    Department department = new Department(1L, "dep1");
       // System.out.println(lang.getString("groupCustomer"));
      //  String group = in.nextLine();
     //   int course = Integer.parseInt(writeFieldValidator("course"));
        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();


        Customer customer = Customer.builder()
                .withName(name)
                .withSurname(surname)
                .withBirthday(splitBirthday(birthday))
              //  .withDepartment(department)
                .withPhoneNumber(phoneNumber)
             //   .withGroup(group)
                .withPassword(password)
              //  .withCourse(course)
                .withEmail(email)
                .build();
        adminController.register(customer);
        System.out.println(lang.getString("CustomerCreated") + "\n");

        menu();

    }
    LocalDate splitBirthday(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthday, formatter);
    }

    void sortCustomer() {
        System.out.println(lang.getString("usersAreSorted") + "\n");
        printAllCustomers(BubbleSort.sort(adminController.findAll()));
    }

    private String writeFieldValidator(String nameField) {

        String key = nameField + "Customer";
        System.out.println(lang.getString(key));
        String fieldInput = in.nextLine();
        if (!ValidatorFactory.getValidator(nameField).validate(fieldInput)) {
            System.out.println(lang.getString("uncorrectedValue"));
            fieldInput = writeFieldValidator(nameField);
        }
        return fieldInput;
    }

    private Customer login(){
        System.out.println("");
        String email = writeFieldValidator("email");
        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();
        return adminController.login(email,password);
    }
    public void print(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            print(customer);
        }
    }

    public void print(Customer Customers) {
        System.out.println(Customers);
    }

    private Customer findById() {
        System.out.println(lang.getString("inputId"));
        return adminController.findById(in.nextLong());
    }
//
//    private ArrayList<Customer> findByDepartment() {
//        System.out.println(lang.getString("inputIdDepartment"));
//        return mainController.findByDepartment(in.nextLong());
//    }
//
//    private ArrayList<Customer> findByGroup() {
//        System.out.println(lang.getString("inputGroup"));
//        String group = in.nextLine();
//        group = in.nextLine();
//        return mainController.findByGroup(group);
//    }
//
//    private ArrayList<Customer> findByDepartmentAndCourse() {
//        System.out.println(lang.getString("inputIdDepartment"));
//        Long department = in.nextLong();
//        System.out.println(lang.getString("inputCourse"));
//        int course = in.nextInt();
//        return mainController.findByDepartmentAndCourse(department, course);
//    }

}
