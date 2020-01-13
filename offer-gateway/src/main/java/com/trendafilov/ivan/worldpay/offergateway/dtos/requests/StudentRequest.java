package com.trendafilov.ivan.worldpay.offergateway.dtos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {

    private Long facultyNumber;
    private String firstName;
    private String lastName;
    private String specialty;
}
