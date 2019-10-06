package com.mytask.helper.validator.impl;

import com.mytask.helper.validator.Validate;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class SurnameValidator implements Validate {
    private static final String SURNAME_PATTERN = "[a-zA-Z]{1,}";



    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(SURNAME_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
