package com.trendafilov.ivan.worldpay.offergateway.dtos.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@ApiModel(description = "Simple Message Response")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;
}
