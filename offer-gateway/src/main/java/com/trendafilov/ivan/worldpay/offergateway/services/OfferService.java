package com.trendafilov.ivan.worldpay.offergateway.services;

import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.OfferRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;

import java.util.List;

public interface OfferService {

    List<OfferResponse> getOfferByMerchantAndStatus(String merchantId, String offerStatus);

    OfferResponse insertOfferForMerchant(String merchantId, OfferRequest offerRequest);

    List<OfferResponse> getAllOffersToStudent(String studentId);
}
