package com.trendafilov.ivan.worldpay.offergateway.exceptions;

public class GatewayServiceException extends RuntimeException {

    private final Integer statusCode;

    public GatewayServiceException(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    public GatewayServiceException(final String message, final Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
