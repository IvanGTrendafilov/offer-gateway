package com.trendafilov.ivan.worldpay.offergateway.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;
import com.trendafilov.ivan.worldpay.offergateway.services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private IRestClient restClient;

    @Override
    public List<OfferResponse> getActiveOffersForMerchant(final String merchantId) {
        final TypeReference<List<OfferResponse>>
            typeReference =
            new TypeReference<List<OfferResponse>>() {
            };

        final List<OfferResponse>
            offerResponses =
            restClient.exchange("http://OFFER-SERVICE:8025/offer/v1/merchants/" + merchantId, null,
                                HttpMethod.GET, null,
                                typeReference,
                                "RANDOM");

        return offerResponses;
    }
}
