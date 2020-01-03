package com.trendafilov.ivan.worldpay.offergateway.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductItemResponse {

    private Long productItemId;
    private String productType;
    private String productDescription;
}
