package com.alpha.hotel.hotelbookingbackend.service.api;

import com.alpha.hotel.hotelbookingbackend.exception.ServiceCallException;
import org.springframework.http.HttpHeaders;

public interface HttpService < T >{

    String post(String url, HttpHeaders httpHeaders, T body) throws ServiceCallException;
}
