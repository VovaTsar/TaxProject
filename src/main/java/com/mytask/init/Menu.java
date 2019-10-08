package com.mytask.init;


import com.mytask.domain.customer.Address;
import com.mytask.domain.customer.Customer;
import com.mytask.domain.customer.Role;
import com.mytask.domain.order.*;
import com.mytask.service.TaxService;
import com.mytask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Menu {

    private UserService userService;
    private TaxService taxService;

    @Autowired
    public Menu(UserService userService, TaxService taxService) {
        this.userService = userService;
        this.taxService = taxService;
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

        MainWork mainWork = new MainWork(5000, Currency.PRIVILEGE, "TS");
        AdditionalWork additionalWork = new AdditionalWork(2000, Currency.NON_PRIVILEGE, "TS");
        ReceiptAsGift receiptAsGift = new ReceiptAsGift(500, Currency.NON_PRIVILEGE, "TS");
        SaleOfProperty saleOfProperty = new SaleOfProperty(3000, Currency.NON_PRIVILEGE, "TS");
        TransferFromAbroad transferFromAbroad = new TransferFromAbroad(4000, Currency.PRIVILEGE, "TS");
        taxService.save(mainWork);
        taxService.save(additionalWork);
        taxService.save(receiptAsGift);
        taxService.save(saleOfProperty);
        taxService.save(transferFromAbroad);
    }
}
