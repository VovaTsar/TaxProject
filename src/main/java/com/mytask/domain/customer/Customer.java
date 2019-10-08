package com.mytask.domain.customer;

import com.mytask.domain.order.Report;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Customer implements Comparable<Customer>, CustomerPrototype {
    private final Long id;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private final Address address;
    private final String phoneNumber;
    private final String email;
    private final String password;
    private final Role role;
    private static Long counter = 0L;
    private Report report;

    private final Comparator<Customer> Customer_COMPARATOR_BY_AGE =
            Comparator.comparingInt(student -> LocalDate.now().getYear() - student.birthday.getYear());

    private final Comparator<Customer> Customer_COMPARATOR_BY_NAME =
            Comparator.comparing(student -> student.name);

    private final Comparator<Customer> Customer_COMPARATOR_BY_SURNAME =
            Comparator.comparing(student -> student.surname);


    public Comparator<Customer> getStudentComparator() {
        return Customer_COMPARATOR_BY_NAME
                .thenComparing(Customer_COMPARATOR_BY_SURNAME
                        .thenComparing(Customer_COMPARATOR_BY_AGE));
    }

    @Override
    public int compareTo(Customer o) {
        return this.getStudentComparator().compare(this, o);
    }

    private Customer(Builder builder) {
        if (builder.id == null) {
            this.id = ++counter;
        } else {
            this.id = builder.id;
        }

        this.name = builder.name;
        this.surname = builder.surname;
        this.birthday = builder.birthday;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(birthday, customer.birthday) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday, address, phoneNumber, email, password);
    }

    @Override
    public CustomerPrototype clone(String newPassword) {
        Address address = (Address) Optional.ofNullable(this.address)
                .map(Address::clone)
                .orElse(null);

        return Customer.builder()
                .withId(id)
                .withName(name)
                .withSurname(surname)
                .withBirthday(birthday)
                .withPassword(newPassword)
                .withAddress(address)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withRole(role)
                .build();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private LocalDate birthday;
        private Address address;
        private String phoneNumber;
        private String email;
        private String password;
        private Role role = Role.USER;

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }


        private Builder() {
        }


        public Customer build() {
            return new Customer(this);
        }


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }


        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
    }
}
