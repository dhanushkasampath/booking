package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;

public interface EmailService {
    void sendEmail(String toAddress, String content, String topic) throws HotelBookingException;
}
