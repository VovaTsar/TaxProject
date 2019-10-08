package com.mytask.init;



import com.mytask.domain.customer.Address;
import com.mytask.domain.customer.Customer;
import com.mytask.domain.customer.Role;
import com.mytask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Menu {

    private UserService userService;

    @Autowired
    public Menu(UserService userService) {
        this.userService = userService;
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
                .withRole(Role.ADMIN)
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
        userService.register(vova);
        userService.register(vania);
        userService.register(vasyl);

    }
}
