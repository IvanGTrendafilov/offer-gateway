package com.trendafilov.ivan.worldpay.offergateway.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentResponse {

    private Long studentId;
    private Long facultyNumber;
    private String firstName;
    private String lastName;
    private String specialty;
}
