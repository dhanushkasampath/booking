package com.alpha.hotel.hotelbookingbackend.exception;

public class ServiceCallException extends Exception{
    private static final long serialVersionUID = -6607397526698735371L;

    public ServiceCallException(String message) {
        super(message);
    }
}
