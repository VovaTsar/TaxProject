package com.mytask.helper.validator.impl;

import com.mytask.helper.validator.Validate;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class PhoneValidator implements Validate {
    private static final String PHONE_PATTERN = "[0-9]{12}";



    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
