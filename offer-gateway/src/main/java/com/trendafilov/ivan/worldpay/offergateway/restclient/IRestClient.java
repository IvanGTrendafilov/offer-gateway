/*
 * Copyright (c) Ivan Trendafilov, Inc. 2018. All Rights Reserved.
 */
package com.trendafilov.ivan.worldpay.offergateway.restclient;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface IRestClient {

    /**
     * {@link org.springframework.web.client.RestTemplate} exchange method with handled status
     * different than 200
     *
     * @param <T>         Request body generic type
     * @param <Q>         Response body generic type
     * @param endpointUri Service URL
     * @param httpHeaders {@link HttpHeaders} Request
     * @param httpMethod  {@link HttpMethod } Request
     * @param body        Body of the request
     * @param response    Response type
     */
    <T, Q> Q exchange(final String endpointUri,
                      final HttpHeaders httpHeaders,
                      final HttpMethod httpMethod, final T body,
                      final Class<Q> response)
    ;

    /**
     * {@link org.springframework.web.client.RestTemplate} exchange method with handled status
     * different than 200
     *
     * @param <T>           Request body generic type
     * @param <Q>           Response body generic type
     * @param endpointUri   Service URL
     * @param httpHeaders   {@link HttpHeaders} Request
     * @param httpMethod    {@link HttpMethod } Request
     * @param body          Body of the request
     * @param typeReference Response type reference
     */
    <T, Q> Q exchange(final String endpointUri,
                      final HttpHeaders httpHeaders,
                      final HttpMethod httpMethod, final T body,
                      final TypeReference<Q> typeReference)
    ;

    <T> ResponseEntity<T> getForEntity(String endpointUri, Class<T> response,
                                       Object... params);
}
