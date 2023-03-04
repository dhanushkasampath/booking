package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;

public interface EncryptDecryptService {
    String encrypt(String stringTobeEncrypted, String secretKey) throws HotelBookingException;

    String decrypt(String stringTobeDecrypted, String secretKey) throws HotelBookingException;
}
