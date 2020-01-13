package com.trendafilov.ivan.worldpay.offergateway.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessagesEnum {
    MERCHANT_NOT_FOUND("Merchant not found!"),
    PRICE_LESS_THAN_ZERO("Offer Price can not be less than 0!"),
    OFFER_NOT_FOUND("Offer not found!"),
    MERCHANT_NAME_EMPTY("Merchant name can not be empty!"),
    MERCHANT_DEPARTMENT_EMPTY("Merchant department can not be empty!"),
    MERCHANT_DOES_NOT_OWN_OFFER("Merchant does not own such Offer!"),
    STUDENT_NOT_FOUND("Student not found!"),
    STUDENT_DOES_NOT_HAVE_SUCH_OFFER("Student does not have such Offer!"),
    STUDENT_HAS_ACCEPTED_OFFER_ALREADY("Student has accepted Offer already"),
    SERVER_ERROR("Internal Server Error. Please contact support team.");

    private final String message;
}
