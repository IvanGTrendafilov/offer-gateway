package com.trendafilov.ivan.worldpay.offergateway.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.CommentRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.OfferRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;
import com.trendafilov.ivan.worldpay.offergateway.enums.OfferStatus;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;
import com.trendafilov.ivan.worldpay.offergateway.services.OfferService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_URL = "http://OFFER-SERVICE:8025/offer/v1";

    private final IRestClient restClient;

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
    public OfferResponse insertOfferForMerchant(final String merchantId,
                                                final OfferRequest offerRequest) {
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

    @Override
    public void cancelMerchantOffer(final String merchantId, final String offerId) {
        restClient.exchange(OFFERS_URL + "/merchants/" + merchantId + "/offers/" + offerId, null,
                HttpMethod.PUT, null,
                String.class);
    }

    @Override
    public OfferResponse assignStudentToOffer(final String studentId, final String offerId,
                                              final String merchantId) {
        final OfferResponse
                exchange =
                restClient.exchange(
                        OFFERS_URL + "/merchants/" + merchantId + "/students/" + studentId + "/offers/"
                                + offerId, null,
                        HttpMethod.PUT, null,
                        OfferResponse.class);
        return exchange;
    }

    @Override
    public void changeOfferStatusForStudent(final String studentId, final String offerId,
                                            final OfferStatus accepted) {
        switch (accepted) {
            case ACCEPTED:
                restClient.exchange(
                        OFFERS_URL + "/students/" + studentId + "/offers/" + offerId + "/accept",
                        null,
                        HttpMethod.PUT, null,
                        String.class);
                break;
            case DECLINED:
                restClient.exchange(
                        OFFERS_URL + "/students/" + studentId + "/offers/" + offerId + "/decline",
                        null,
                        HttpMethod.PUT, null,
                        String.class);
                break;
            default:
        }
    }

    @Override
    public void commentOffer(final String offerId, final CommentRequest commentRequest) {
        restClient.exchange(OFFERS_URL + "/offers/" + offerId + "/comment", null,
                HttpMethod.POST, commentRequest,
                OfferResponse.class);
    }

}
