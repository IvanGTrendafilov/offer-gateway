package com.trendafilov.ivan.worldpay.offergateway.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.OfferRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;
import com.trendafilov.ivan.worldpay.offergateway.services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_URL = "http://OFFER-SERVICE:8025/offer/v1/";
    @Autowired
    private IRestClient restClient;

    @Override
    public List<OfferResponse> getOfferByMerchantAndStatus(final String merchantId,
                                                           final String offerStatus) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("offer-status", offerStatus);
        final TypeReference<List<OfferResponse>>
            typeReference =
            new TypeReference<List<OfferResponse>>() {
            };

        final List<OfferResponse>
            offerResponses =
            restClient.exchange(OFFERS_URL + "/merchants/" + merchantId, httpHeaders,
                                HttpMethod.GET, null,
                                typeReference
            );
        return offerResponses;
    }

    @Override
    public OfferResponse insertOfferForMerchant(final String merchantId, final OfferRequest offerRequest) {
        final OfferResponse
            exchange =
            restClient.exchange(OFFERS_URL + "/merchants/" + merchantId, null,
                                HttpMethod.POST, offerRequest,
                                OfferResponse.class);
        return exchange;
    }

    @Override
    public List<OfferResponse> getAllOffersToStudent(final String studentId) {
        final TypeReference<List<OfferResponse>>
            typeReference =
            new TypeReference<List<OfferResponse>>() {
            };

        final List<OfferResponse>
            offerResponses =
            restClient.exchange(OFFERS_URL + "/students/" + studentId, null,
                                HttpMethod.GET, null,
                                typeReference
            );
        return offerResponses;
    }
}
