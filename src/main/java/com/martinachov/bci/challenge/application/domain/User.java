package com.martinachov.bci.challenge.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;

    private String token;
    private Boolean isActive;
    private LocalDateTime created;
    private LocalDateTime lastLogin;

}