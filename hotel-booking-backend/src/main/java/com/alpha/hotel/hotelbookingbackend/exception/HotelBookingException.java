package com.alpha.hotel.hotelbookingbackend.exception;

import org.springframework.http.HttpStatus;

public class HotelBookingException extends Exception {

    private static final long serialVersionUID = 2881116644451554263L;

    private final HttpStatus httpStatusCode;

    public HotelBookingException(HttpStatus httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

}
