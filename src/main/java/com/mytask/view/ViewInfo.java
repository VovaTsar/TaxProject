package com.mytask.view;

import com.mytask.controller.AdminController;
import com.mytask.controller.UserController;
import com.mytask.domain.Customer;
import com.mytask.domain.Role;
import com.mytask.helper.utillity.Converter;
import com.mytask.helper.sort.BubbleSort;
import com.mytask.helper.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ViewInfo {

    private UserController userController;
    private AdminController adminController;
    private Customer currentCustomer;
    private ResourceBundle language;

    @Autowired
    public ViewInfo(UserController userController, AdminController adminController) {
        this.userController = userController;
        this.adminController = adminController;
    }

    private Scanner in = new Scanner(System.in);

    public void run() {
        chooseMenuLang();
    }


    protected void chooseMenuLang() {
        System.out.println("Choose language/Оберіть мову");
        System.out.println("English (1)");
        System.out.println("Українська (2)");
        int chooseLanguage = in.nextInt();

        chooseLang(chooseLanguage);
    }

    private void chooseLang(int chooseLang) {

        try {
            if (chooseLang == 1) {
                language = ResourceBundle.getBundle("resources", new Locale("en"), new Converter());
            } else if (chooseLang == 2) {
                language = ResourceBundle.getBundle("resources", new Locale("ua"), new Converter());
            } else
                chooseMenuLang();
        } catch (Exception e) {
            throw new IllegalArgumentException(language.getString("uncorrectedArgument"));
        }
        loginOrRegister();
    }

    private void loginOrRegister() {
        System.out.println("1 - " + language.getString("registration"));
        System.out.println("2 - " + language.getString("login"));
        int loginOrRegister = in.nextInt();

        if (loginOrRegister == 1) {
            register();
        } else if (loginOrRegister == 2) {
            login();
        } else {
            loginOrRegister();
        }
    }

    private void menu() {
        if (currentCustomer.getRole() == Role.ADMIN) {
            menuAdmin();
        } else {
            menuUser();
        }
    }

    public void menuUser() {
        System.out.println(language.getString("menu"));
        System.out.println("1 - " + language.getString("viewInfoUser"));
        System.out.println("8 - " + language.getString("chooseLanguage"));
        System.out.println("9 - " + language.getString("exit"));

        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(language.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                System.out.println(userController.findById(currentCustomer.getId()));
                break;

            case 8:
                chooseMenuLang();
                break;
            case 9:
                System.exit(0);
        }
        menuUser();
    }

    private void menuAdmin() {
        System.out.println(language.getString("menu"));
        System.out.println("1 - " + language.getString("viewCustomer"));
        System.out.println("2 - " + language.getString("sortCustomer"));
        System.out.println("3 - " + language.getString("inputId"));
        System.out.println("9 - " + language.getString("chooseLanguage"));
        System.out.println("0 - " + language.getString("exit"));

        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(language.getString("uncorrectedArgument"));
        }

        switch (choice) {

            case 1:
                printAllCustomers(BubbleSort.sort(adminController.findAll()));
                break;

            case 2:
                sortCustomer();
                break;
            case 3:
                System.out.println(findById());
                break;

            case 9:
                chooseMenuLang();
                break;
            case 0:
                System.exit(0);
        }
        menuAdmin();
    }

    void printAllCustomers(ArrayList<Customer> customers) {
        if (adminController.findAll().isEmpty())
            System.out.println(language.getString("noCustomerYet"));
        else {
            System.out.println("\n" + language.getString("listCustomer"));
            for (Customer customer : customers
            ) {
                System.out.println(customer);
            }
            System.out.println();
        }
    }

    void sortCustomer() {
        System.out.println(language.getString("usersAreSorted") + "\n");
        printAllCustomers(BubbleSort.sort(adminController.findAll()));
    }

    private Customer findById() {
        System.out.println(language.getString("inputId"));
        return adminController.findById(in.nextLong());
    }


    void register() {

        String name = writeFieldValidator("name");
        String surname = writeFieldValidator("surname");
        String email = writeFieldValidator("email");
        String phoneNumber = writeFieldValidator("phoneNumber");
        String birthday = writeFieldValidator("date");
        System.out.println(language.getString("passwordCustomer"));
        String password = in.nextLine();


        Customer customer = Customer.builder()
                .withName(name)
                .withSurname(surname)
                .withBirthday(splitBirthday(birthday))
                .withPhoneNumber(phoneNumber)
                .withPassword(password)
                .withEmail(email)
                .build();

        userController.register(customer);
        System.out.println(language.getString("CustomerCreated") + "\n");
        currentCustomer = customer;


    }

    LocalDate splitBirthday(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthday, formatter);
    }


    private String writeFieldValidator(String nameField) {

        String key = nameField + "Customer";
        System.out.println(language.getString(key));
        String fieldInput = in.nextLine();
        if (!ValidatorFactory.getValidator(nameField).validate(fieldInput)) {
            System.out.println(language.getString("uncorrectedValue"));
            fieldInput = writeFieldValidator(nameField);
        }
        return fieldInput;
    }

    private void login() {
        System.out.println("");
        String email = writeFieldValidator("email");
        System.out.println(language.getString("passwordCustomer"));
        String password = in.nextLine();
        currentCustomer = userController.login(email, password);
        menu();
    }

    public void print(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            print(customer);
        }
    }

    public void print(Customer Customers) {
        System.out.println(Customers);
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
