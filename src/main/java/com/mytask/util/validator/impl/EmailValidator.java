package com.mytask.util.validator.impl;

import com.mytask.util.validator.Validate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public  class EmailValidator implements Validate {
    private static final Logger LOGGER = Logger.getLogger(EmailValidator.class);
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}";


    @Override
    public boolean validate(final String hex) {
        LOGGER.info("Validate Customer email ");
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        if (matcher.matches()) {
            LOGGER.info("Email is correct");
        } else {
            LOGGER.error("Email is not correct");
        }
        return matcher.matches();
    }
}
