package com.trendafilov.ivan.worldpay.offergateway.services;

import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.MerchantRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.MerchantResponse;

import java.util.List;

public interface MerchantService {

    MerchantResponse findMerchantByMerchantId(String merchantId);

    List<MerchantResponse> getAllMerchants();

    MerchantResponse createMerchantByMerchantRequest(MerchantRequest merchantRequest);
}
