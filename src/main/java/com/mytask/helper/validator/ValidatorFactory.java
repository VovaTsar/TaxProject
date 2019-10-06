package com.mytask.helper.validator;

import com.mytask.helper.validator.impl.*;
import org.springframework.stereotype.Component;

@Component
public final class ValidatorFactory {

    public static Validate getValidator(String field) {
        switch (field) {
            case "email":
                return new EmailValidator();
            case "name":
                return new NameValidator();
            case "surname":
                return new SurnameValidator();
            case "phoneNumber":
                return new PhoneValidator();
            case "date":
                return new DataValidator();
            case "course":
                return new CourseValidator();
        }
        throw new IllegalArgumentException();
    }
}
