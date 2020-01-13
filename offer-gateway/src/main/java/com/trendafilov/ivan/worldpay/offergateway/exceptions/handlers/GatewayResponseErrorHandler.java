package com.trendafilov.ivan.worldpay.offergateway.exceptions.handlers;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GatewayResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(final ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(final ClientHttpResponse response) throws IOException {
        log.info("Error during calling another API with status test: {} and HTTP Status code: {}",
                 response.getStatusText(), response.getRawStatusCode());
    }
}
