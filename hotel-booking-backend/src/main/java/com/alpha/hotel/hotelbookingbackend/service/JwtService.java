package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;

public interface JwtService {

    boolean validateToken(String token);
    boolean validateTokenWithOneTimeUse(String token) throws HotelBookingException;
}
