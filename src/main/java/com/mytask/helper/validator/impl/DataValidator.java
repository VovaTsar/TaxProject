package com.mytask.helper.validator.impl;

import com.mytask.helper.validator.Validate;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class DataValidator implements Validate {
    private static final String DATA_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";



    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(DATA_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
