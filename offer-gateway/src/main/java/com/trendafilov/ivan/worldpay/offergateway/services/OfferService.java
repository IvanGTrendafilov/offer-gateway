package com.trendafilov.ivan.worldpay.offergateway.services;

import com.trendafilov.ivan.worldpay.offergateway.dtos.requests.OfferRequest;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;
import com.trendafilov.ivan.worldpay.offergateway.enums.OfferStatus;

import java.util.List;

public interface OfferService {

    List<OfferResponse> getOfferByMerchantAndStatus(String merchantId, String offerStatus);

    OfferResponse insertOfferForMerchant(String merchantId, OfferRequest offerRequest);

    List<OfferResponse> getAllOffersToStudent(String studentId);

    void cancelMerchantOffer(String merchantId, String offerId);

    OfferResponse assignStudentToOffer(String studentId, String offerId, String merchantId);

    void changeOfferStatusForStudent(String studentId, String offerId, OfferStatus accepted);

}
