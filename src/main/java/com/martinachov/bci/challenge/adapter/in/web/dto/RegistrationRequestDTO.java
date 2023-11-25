package com.martinachov.bci.challenge.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.martinachov.bci.challenge.application.domain.RegEx.REG_EXP_FOR_EMAIL;
import static com.martinachov.bci.challenge.application.domain.RegEx.REG_EXP_FOR_PASSWORD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {

    private String name;

    @NotNull(message = "Email cannot be empty!")
    @Email(regexp = REG_EXP_FOR_EMAIL , message = "Invalid email address. RegularExpression")
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Pattern(regexp = REG_EXP_FOR_PASSWORD, message = "The password format is incorrect. RegularExpression" )
    private String password;

    private List<PhoneDTO> phones;
}
