package com.alpha.hotel.hotelbookingbackend.service.api.impl;

import com.alpha.hotel.hotelbookingbackend.exception.ServiceCallException;
import com.alpha.hotel.hotelbookingbackend.service.api.HttpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Service
public class HttpServiceImpl<T> implements HttpService<T> {
    private final Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity< String > post(String url, HttpHeaders httpHeaders, T body) throws ServiceCallException {
        return call(getURI(url, null, body), httpHeaders, body, HttpMethod.POST);
    }

    private ResponseEntity< String > call(URI uri, HttpHeaders httpHeaders, T body, HttpMethod httpMethod) throws ServiceCallException {
        try {
            HttpEntity<T> httpEntity = getHttpHeaderEntity(httpHeaders, body);
            return restTemplate.exchange(uri, httpMethod, httpEntity, String.class);
        }
        catch ( HttpClientErrorException e ) {
            throw new ServiceCallException("Service call error : " + e.getResponseBodyAsString());
        }
        catch ( RestClientException e ) {
            throw new ServiceCallException("RestClientException occurred : " + e.getMessage());
        }
    }

    private HttpEntity<T> getHttpHeaderEntity(HttpHeaders headers, T body) {
        if ( headers == null ) {
            headers = new HttpHeaders();
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity <>(body, headers);
    }

    private URI getURI(String url, Map<String, Object> queryParameters, T body) {

        try {
            String bodyString = null;
            if (body != null) {
                bodyString = objectMapper.writeValueAsString(body);
            }
            logger.info("Request body : {}, url : {}, query-params : {}", bodyString, url, queryParameters);
        } catch (JsonProcessingException e) {
            logger.warn("Request body json convert exception : {}, request-body : {}, url : {}, query-params : {}", e.getMessage(), body, url, queryParameters);
        }

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        if ((queryParameters != null) && (!queryParameters.isEmpty())) {
            queryParameters.forEach((k, v) -> uriComponentsBuilder.queryParam(k, v));
        }
        return uriComponentsBuilder.buildAndExpand().toUri();
    }
}
