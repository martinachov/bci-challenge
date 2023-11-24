package com.martinachov.bci.challenge.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    private Long number;
    private Integer cityCode;
    private String countryCode;

}
