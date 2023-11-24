package com.martinachov.bci.challenge.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationData {
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}