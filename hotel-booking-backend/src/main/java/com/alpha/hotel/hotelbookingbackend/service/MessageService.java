package com.alpha.hotel.hotelbookingbackend.service;

public interface MessageService {
    void sendSms(String phoneNumber, String content, String token);
}
