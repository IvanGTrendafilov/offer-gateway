package com.trendafilov.ivan.worldpay.offergateway.services;

import com.trendafilov.ivan.worldpay.offergateway.dtos.response.OfferResponse;

import java.util.List;

public interface OfferService {

    /**
     * Get all active offers for merchant
     *
     * @param merchantId
     * @return List of {@link OfferResponse}
     * @throws OfferServiceException If there isn't such merchant into database
     */
    List<OfferResponse> getActiveOffersForMerchant(String merchantId);
}
