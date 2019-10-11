package com.mytask.view;

import com.mytask.controller.AdminController;
import com.mytask.controller.TaxController;
import com.mytask.controller.UserController;
import com.mytask.domain.customer.Customer;
import com.mytask.domain.customer.Role;
import com.mytask.domain.order.Tax;
import com.mytask.exeption.MyRuntimeException;
import com.mytask.util.localization.UTF8Converter;
import com.mytask.util.sort.BubbleSort;
import com.mytask.util.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

@Component
public class ViewInfo {

    private UserController userController;
    private AdminController adminController;
    private TaxController taxController;
    private Customer currentCustomer;

    private ResourceBundle language;

    @Autowired
    public ViewInfo(UserController userController, AdminController adminController, TaxController taxController) {
        this.userController = userController;
        this.adminController = adminController;
        this.taxController = taxController;
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
                language = ResourceBundle.getBundle("resources", new Locale("en"), new UTF8Converter());
            } else if (chooseLang == 2) {
                language = ResourceBundle.getBundle("resources", new Locale("ua"), new UTF8Converter());
            } else
                chooseMenuLang();
        } catch (RuntimeException e) {
            chooseLang(chooseLang);
        }
        loginOrRegister();
    }

    private void loginOrRegister() {
        try {
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
        } catch (MyRuntimeException e) {
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
        try {
            System.out.println(language.getString("menu"));
            System.out.println("1 - " + language.getString("currentId"));
            System.out.println("2 - " + language.getString("viewAllTaxes"));
            System.out.println("3 - " + language.getString("viewOwnTaxes"));
            System.out.println("4 - " + language.getString("addOwnTaxes"));
            System.out.println("5 - " + language.getString("deleteOwnTaxes"));
            System.out.println("6 - " + language.getString("sortOwnTaxes"));
            System.out.println("7 - " + language.getString("sumOwnTaxes"));
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
                case 2:
                    printAllTaxes(taxController.findAll());
                    break;
                case 3:
                    printAllTaxes(userController.findAllTaxes(currentCustomer));
                    break;
                case 4:
                    addOwnTaxesUser();
                    break;
                case 5:
                    deleteOwnTaxesUser();
                    break;
                case 6:
                    printAllTaxes(userController.sortTax(currentCustomer));
                    break;
                case 7:
                    System.out.println(userController.sumOfTaxes(currentCustomer));
                    break;
                case 8:
                    chooseMenuLang();
                    break;
                case 9:
                    System.exit(0);
            }
            menuUser();
        } catch (MyRuntimeException e) {
            menuUser();
        }
    }

    private void deleteOwnTaxesUser() {
        System.out.println(language.getString("inputId"));
        Long id = in.nextLong();
        userController.deleteTax(currentCustomer, id);
    }

    private void addOwnTaxesUser() {
        System.out.println(language.getString("inputId"));
        Long id = in.nextLong();
        userController.addTax(currentCustomer, id);
    }


    private void menuAdmin() {
        try {
            System.out.println(language.getString("menu"));
            System.out.println("1 - " + language.getString("viewCustomer"));
            System.out.println("2 - " + language.getString("sortCustomer"));
            System.out.println("3 - " + language.getString("inputIdUser"));
            System.out.println("4 - " + language.getString("viewAllTaxes"));
            System.out.println("5 - " + language.getString("deleteTaxes"));
            System.out.println("6 - " + language.getString("viewOwnTaxes"));
            System.out.println("7 - " + language.getString("addOwnTaxes"));
            System.out.println("8 - " + language.getString("deleteOwnTaxes"));
            System.out.println("9 - " + language.getString("sortOwnTaxes"));
            System.out.println("10 - " + language.getString("sumOwnTaxes"));
            System.out.println("11 - " + language.getString("chooseLanguage"));
            System.out.println("12 - " + language.getString("exit"));

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
                case 4:
                    printAllTaxes(taxController.findAll());
                    break;
                case 5:
                    deleteTax();
                    break;
                case 6:
                    printAllTaxes(adminController.findAllTaxes(currentCustomer));
                    break;
                case 7:
                    addOwnTaxes();
                    break;
                case 8:
                    deleteOwnTaxes();
                    break;
                case 9:
                    printAllTaxes(adminController.sortTax(currentCustomer));
                    break;
                case 10:
                    System.out.println(adminController.sumOfTaxes(currentCustomer));
                    break;
                case 11:
                    chooseMenuLang();
                    break;
                case 12:
                    System.exit(0);
            }
            menuAdmin();
        } catch (MyRuntimeException e) {
            menuAdmin();
        }
    }

    private void deleteOwnTaxes() {
        System.out.println(language.getString("inputId"));
        Long id = in.nextLong();
        adminController.deleteTax(currentCustomer, id);
    }

    private void addOwnTaxes() {
        System.out.println(language.getString("inputId"));
        Long id = in.nextLong();
        adminController.addTax(currentCustomer, id);
    }

    private void deleteTax() {
        System.out.println(language.getString("inputId"));
        Long id = in.nextLong();
        taxController.deleteById(id);
    }

    void printAllCustomers(ArrayList<Customer> customers) {
        if (customers.isEmpty())
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

    void printAllTaxes(ArrayList<Tax> taxes) {
        if (taxes.isEmpty())
            System.out.println(language.getString("noTaxesYet"));
        else {
            System.out.println("\n" + language.getString("listTaxes"));
            for (Tax tax : taxes
            ) {
                System.out.println(tax);
            }
            System.out.println();
        }
    }

    void register() {

        try {
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

            menu();
        } catch (MyRuntimeException e) {
            register();
        }
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


        try {
            System.out.println("");
            String email = writeFieldValidator("email");
            System.out.println(language.getString("passwordCustomer"));
            String password = in.nextLine();
            currentCustomer = userController.login(email, password);
            menu();
        } catch (MyRuntimeException e) {
            login();
        }
    }


}
