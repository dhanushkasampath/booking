package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.ServiceCallException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageService extends GenericService {
    void sendSms(String phoneNumber, String content) throws ServiceCallException, JsonProcessingException;
}
