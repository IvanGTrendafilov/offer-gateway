package com.trendafilov.ivan.worldpay.offergateway.controllers;

import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.OfferRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;
import com.trendafilov.ivan.worldpay.offergateway.services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/offers/v1")
@Slf4j
public class OfferGatewayController {

    @Autowired
    private OfferService offerService;


    @ApiOperation(
        value = "Get all offers for merchant by specific status",
        produces = MediaType.APPLICATION_JSON_VALUE,
        notes = "Get all offers for merchant for specific offer status. GatewayServiceException is thrown when merchant is invalid",
        response = OfferResponse.class)
    @GetMapping(value = "merchants/{merchantId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOffersForMerchantByStatus(
        @RequestHeader(name = "offer-status") final String offerStatus,
        @PathVariable final String merchantId) {
        log.info("Get all Offers for merchant with Id: {}", merchantId);
        final List<OfferResponse>
            offerByMerchantAndStatus =
            offerService.getOfferByMerchantAndStatus(merchantId, offerStatus);
        return new ResponseEntity<>(offerByMerchantAndStatus, HttpStatus.OK);
    }

    @ApiOperation(
        value = "Insert information for offer",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        notes = "Insert merchant offer for specific product items. OfferServiceException is thrown when merchant is invalid",
        response = OfferResponse.class)
    @PostMapping(value = "merchants/{merchantId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertOffer(@PathVariable final String merchantId,
                                      @Valid @RequestBody final OfferRequest offerRequest) {
        log.info("Insert offer endpoint with merchant id: {} and OfferRequest Request body: {}",
                 merchantId, offerRequest);
        final OfferResponse
            offerResponse =
            offerService.insertOfferForMerchant(merchantId, offerRequest);
        return new ResponseEntity<>(offerResponse, HttpStatus.CREATED);
    }

    @ApiOperation(
        value = "Get all offers to Student.",
        produces = MediaType.APPLICATION_JSON_VALUE,
        notes = "Get all offers for Student. GatewayServiceException is thrown when Student is invalid",
        response = OfferResponse.class)
    @GetMapping(value = "students/{studentId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllOffersToStudent(
        @PathVariable final String studentId) {
        log.info("Get all Offers for student with Id: {}", studentId);
        final List<OfferResponse>
            allOffersToStudent =
            offerService.getAllOffersToStudent(studentId);
        return new ResponseEntity<>(allOffersToStudent, HttpStatus.OK);
    }
}
