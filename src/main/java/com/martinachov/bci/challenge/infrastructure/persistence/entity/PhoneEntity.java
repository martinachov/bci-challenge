package com.martinachov.bci.challenge.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private Long number;
    private Integer cityCode;
    private String countryCode;
}
