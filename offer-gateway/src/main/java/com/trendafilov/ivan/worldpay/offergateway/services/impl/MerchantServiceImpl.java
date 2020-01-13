package com.trendafilov.ivan.worldpay.offergateway.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.MerchantRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.MerchantResponse;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;
import com.trendafilov.ivan.worldpay.offergateway.services.MerchantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    private static final String MERCHANT_URL = "http://OFFER-SERVICE:8025/merchant/v1/";

    @Autowired
    private IRestClient restClient;

    @Override
    public MerchantResponse findMerchantByMerchantId(final String merchantId) {
        final MerchantResponse merchantResponse =
            restClient.exchange(MERCHANT_URL + merchantId, null,
                                HttpMethod.GET, null,
                                MerchantResponse.class
            );
        return merchantResponse;
    }

    @Override
    public List<MerchantResponse> getAllMerchants() {
        final TypeReference<List<MerchantResponse>>
            typeReference =
            new TypeReference<List<MerchantResponse>>() {
            };

        final List<MerchantResponse>
            merchantResponses =
            restClient.exchange(MERCHANT_URL, null,
                                HttpMethod.GET, null,
                                typeReference
            );
        return merchantResponses;
    }

    @Override
    public MerchantResponse createMerchantByMerchantRequest(final MerchantRequest merchantRequest) {
        final MerchantResponse merchantResponse =
            restClient.exchange(MERCHANT_URL, null,
                                HttpMethod.POST, merchantRequest,
                                MerchantResponse.class
            );
        return merchantResponse;
    }
}
