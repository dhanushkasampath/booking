package com.alpha.hotel.hotelbookingbackend.service.api;

import com.alpha.hotel.hotelbookingbackend.exception.ServiceCallException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface HttpService < T >{

    ResponseEntity< String > post(String url, HttpHeaders httpHeaders, T body) throws ServiceCallException;
}
