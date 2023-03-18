package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface JwtService extends GenericService {

    boolean validateToken(String token);
    boolean validateTokenWithOneTimeUse(String token) throws HotelBookingException;
    Map<String, Object> decodeJwt(String jwtToken) throws HotelBookingException;
    String extractUserPropertyFromJwtToken(String requestUserProperty, HttpServletRequest request) throws HotelBookingException;
    String extractJwtTokenFromHeader(HttpServletRequest request) throws HotelBookingException;
}
