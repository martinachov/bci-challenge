package com.martinachov.bci.challenge.application.domain;

import org.springframework.beans.factory.annotation.Value;

public class RegEx {

    @Value("${application.validation.regex.email}")
    public static final String REG_EXP_FOR_EMAIL = ".+@.+\\..+";

    @Value("${application.validation.regex.pass}")
    public static final String REG_EXP_FOR_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
}
