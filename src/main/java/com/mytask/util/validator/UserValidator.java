package com.mytask.util.validator;

import com.mytask.domain.customer.Customer;
import com.mytask.util.validator.impl.EmailValidator;
import com.mytask.util.validator.impl.NameValidator;
import com.mytask.util.validator.impl.PhoneValidator;
import com.mytask.util.validator.impl.SurnameValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private static final Logger LOGGER = Logger.getLogger(UserValidator.class);
    private EmailValidator emailValidator;
    private NameValidator nameValidator;
    private PhoneValidator phoneValidator;
    private SurnameValidator surnameValidator;

    @Autowired
    public UserValidator(EmailValidator emailValidator, NameValidator nameValidator,
                         PhoneValidator phoneValidator, SurnameValidator surnameValidator) {
        this.emailValidator = emailValidator;
        this.nameValidator = nameValidator;
        this.phoneValidator = phoneValidator;
        this.surnameValidator = surnameValidator;
    }

    public boolean validate(Customer customer) {
        LOGGER.info("Validate UserValidator ");
        boolean validate = emailValidator.validate(customer.getEmail()) &&
                nameValidator.validate(customer.getName()) &&
                phoneValidator.validate(customer.getPhoneNumber()) &&
                surnameValidator.validate(customer.getSurname());
        if (validate) {
            LOGGER.info("Customer validate successful");
        } else {
            LOGGER.error("UserValidator error");

        }
        return validate;
    }
}