package com.trendafilov.ivan.worldpay.offergateway.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferResponse {

    private MerchantResponse merchantResponse;
    private Long offerId;
    private String description;
    private Date expireDate;
    private String status;
    private String currency;
    private BigDecimal price;
    private StudentResponse studentResponse;
    private List<ProductItemResponse> productItemResponses;
    private List<Comment> commentResponses;

}
