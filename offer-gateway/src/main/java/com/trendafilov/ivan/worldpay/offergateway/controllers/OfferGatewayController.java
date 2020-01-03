package com.trendafilov.ivan.worldpay.offergateway.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;
import com.trendafilov.ivan.worldpay.offergateway.services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/offers")
@Slf4j
public class OfferGatewayController {

    @Autowired
    private IRestClient restClient;

    @Autowired
    private OfferService offerService;

    @GetMapping("/all")
    public Collection<OfferResponse> getOfferDescriptions() {
        final TypeReference<List<OfferResponse>>
            typeReference =
            new TypeReference<List<OfferResponse>>() {
            };

        final List<OfferResponse>
            offerResponses =
            restClient.exchange("http://OFFER-SERVICE:8025/offer/v1/merchants/1", null,
                                HttpMethod.GET, null,
                                typeReference,
                                "RANDOM");

        return offerResponses;
    }

    @ApiOperation(
        value = "Gert all active offers for merchant",
        produces = MediaType.APPLICATION_JSON_VALUE,
        notes = "Gert all active offers for merchant. OfferServiceException is thrown when merchant is invalid",
        response = OfferResponse.class)
    @GetMapping(value = "merchants/{merchantId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getActiveOffers(@PathVariable final String merchantId) {
        log.info("Get all Offers for merchant with Id: {}", merchantId);
        final List<OfferResponse>
            activeOffersForMerchant =
            offerService.getActiveOffersForMerchant(merchantId);
        return new ResponseEntity<>(activeOffersForMerchant, HttpStatus.OK);
    }
}
