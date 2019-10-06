package com.mytask.init;



import com.mytask.domain.Address;
import com.mytask.domain.Customer;
import com.mytask.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Menu {

    private CustomerService customerService;

    @Autowired
    public Menu(CustomerService customerService) {
        this.customerService = customerService;
    }


    public void run() {
        Customer vova = Customer.builder()
                .withName("Vova")
                .withSurname("Ts")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withPassword("12345")
                .withAddress(new Address("Kyiv", "WWW", 7))
                .withPhoneNumber("3807341345")
                .withEmail("vova@gmail.com")
                .build();

        Customer vania = Customer.builder()
                .withName("Vania")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withPassword("12345")
                .withAddress(new Address("Kyiv", "WWW", 8))
                .withPhoneNumber("38043545345")
                .withEmail("vania@gmail.com")
                .build();

        Customer vasyl = Customer.builder()
                .withName("Vasyl")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withPassword("12345")
                .withAddress(new Address("Kyiv", "WWW", 8))
                .withPhoneNumber("38063355345")
                .withEmail("vasyl@gmail.com")
                .build();
        customerService.register(vova);
        customerService.register(vania);
        customerService.register(vasyl);

    }
}
