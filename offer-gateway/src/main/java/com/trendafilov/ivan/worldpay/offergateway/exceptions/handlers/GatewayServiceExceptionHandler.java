package com.trendafilov.ivan.worldpay.offergateway.exceptions.handlers;

import com.trendafilov.ivan.worldpay.offergateway.dtos.response.ErrorResponse;
import com.trendafilov.ivan.worldpay.offergateway.exceptions.GatewayServiceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GatewayServiceExceptionHandler {

    @ExceptionHandler(GatewayServiceException.class)
    public final ResponseEntity handleSpecificExceptions(final GatewayServiceException exception) {
        final HttpStatus httpStatus = HttpStatus.valueOf(exception.getStatusCode());
        final ErrorResponse
            errorResponse =
            ErrorResponse.builder()
                         .message(exception.getMessage())
                         .build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
