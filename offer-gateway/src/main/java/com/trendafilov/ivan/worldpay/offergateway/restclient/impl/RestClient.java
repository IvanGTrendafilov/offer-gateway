/*
 * Copyright (c) Ivan Trendafilov, Inc. 2018. All Rights Reserved.
 */
package com.trendafilov.ivan.worldpay.offergateway.restclient.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendafilov.ivan.worldpay.offergateway.dtos.response.ErrorResponse;
import com.trendafilov.ivan.worldpay.offergateway.enums.ErrorMessagesEnum;
import com.trendafilov.ivan.worldpay.offergateway.exceptions.GatewayServiceException;
import com.trendafilov.ivan.worldpay.offergateway.restclient.IRestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestClient implements IRestClient {


    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestClient(final RestTemplate restTemplate,
                      final ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T, Q> Q exchange(final String endpointUri,
                             final HttpHeaders httpHeaders,
                             final HttpMethod httpMethod, final T body,
                             final Class<Q> response) {

        if (response == null) {
            log.warn("Response should not be null");
            throw new GatewayServiceException(ErrorMessagesEnum.SERVER_ERROR.getMessage(),
                                              HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        final ResponseMapper<Q> mapper =
            bodyAsString -> objectMapper.readValue(bodyAsString, response);
        final String responseName = response.getName();

        return this.exchange(endpointUri, httpHeaders, httpMethod, body, mapper,
                             responseName);
    }

    @Override
    public <T, Q> Q exchange(final String endpointUri,
                             final HttpHeaders httpHeaders,
                             final HttpMethod httpMethod, final T body,
                             final TypeReference<Q> typeReference) {

        if (typeReference == null) {
            log.warn("Type reference should not be null");
            throw new GatewayServiceException(ErrorMessagesEnum.SERVER_ERROR.getMessage(),
                                              HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        final ResponseMapper<Q> mapper =
            bodyAsString -> objectMapper.readValue(bodyAsString, typeReference);
        final String
            responseName =
            typeReference.getType()
                         .getTypeName();

        return this.exchange(endpointUri, httpHeaders, httpMethod, body, mapper,
                             responseName);
    }

    private <T, Q> Q exchange(final String endpointUri,
                              final HttpHeaders httpHeaders,
                              final HttpMethod httpMethod, final T body,
                              final ResponseMapper<Q> responseMapper,
                              final String responseName) {
        log.info("Calling external API with http method: {} and URL: {} ", httpMethod.toString(),
                 endpointUri);

        final HttpEntity<T> entity = new HttpEntity<>(body, getHttpHeaders(httpHeaders));
        final ResponseEntity<String> responseEntity =
            restTemplate.exchange(endpointUri, httpMethod, entity, String.class);
        final HttpStatus httpStatus = responseEntity.getStatusCode();

        final String responseEntityBody = responseEntity.getBody();

        log.debug(
            "Sent request to: {}, with HTTP verb: {}, headers: {}, body: {}, and expected object: {}",
            endpointUri, httpMethod.name(), httpHeaders, body, responseName);
        log.debug("Received status: {} with body {} and headers: {}",
                  httpStatus.value(), responseEntityBody, responseEntity.getHeaders());

        if (responseEntityBody == null || responseEntityBody.isEmpty()) {
            return null;
        }

        if (!httpStatus.is2xxSuccessful()) {
            handleSpringboardError(responseEntity, httpStatus);
        }

        Q responseBody = null;
        try {
            responseBody = responseMapper.map(responseEntityBody);
        } catch (final IOException e) {
            log.error(
                "Unable to convert response body during exchange");
            throw new GatewayServiceException(ErrorMessagesEnum.SERVER_ERROR.getMessage(),
                                              HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseBody;
    }

    private void handleSpringboardError(final ResponseEntity<String> responseEntity,
                                        final HttpStatus httpStatus) {
        try {
            final ErrorResponse
                responseError =
                objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
            throw new GatewayServiceException(responseError.getMessage(),
                                              responseEntity.getStatusCodeValue());
        } catch (final IOException e) {
            log.error(
                "Unable to convert response body during exchange ");
            throw new GatewayServiceException(responseEntity.getBody(), httpStatus.value());
        }
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(final String endpointUri, final Class<T> response,
                                              final Object... params) {
        final ResponseEntity<T>
            forEntity =
            restTemplate.getForEntity(endpointUri, response, params);
        return forEntity;
    }

    private synchronized HttpHeaders getHttpHeaders(HttpHeaders httpHeaders) {

        if (!Optional.ofNullable(httpHeaders)
                     .isPresent()) {
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        }

        return httpHeaders;
    }

    @FunctionalInterface
    private interface ResponseMapper<R> {

        R map(String body) throws IOException;
    }
}

