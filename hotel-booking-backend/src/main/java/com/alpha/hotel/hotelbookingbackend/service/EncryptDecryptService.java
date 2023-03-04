package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;

public interface EncryptDecryptService {
    String encrypt(String providedPassword, String secretKey) throws HotelBookingException;
}
