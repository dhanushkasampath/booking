package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;

public interface GenericService {
    Logger logger = LoggerFactory.getLogger(GenericService.class);
    ObjectMapper mapper = new ObjectMapper();

    ModelMapper modelMapper = new ModelMapper();

    default < T > T map(Object sourceEntity, Class < T > destinationType) throws HotelBookingException {
        try {
            return modelMapper.map(sourceEntity, destinationType);
        }
        catch (IllegalArgumentException | ConfigurationException | MappingException e ) {
            logger.error("Source:{}, to destination by class type: {}, mapping exception: ", sourceEntity, destinationType, e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Mapping exception occurred: " + e.getMessage());
        }
    }

    default < T > T map(Object sourceEntity, Type destinationType) throws HotelBookingException {
        try {
            return modelMapper.map(sourceEntity, destinationType);
        }
        catch ( IllegalArgumentException | ConfigurationException | MappingException e ) {
            logger.error("Source:{}, to destination by type:{}, mapping exception : ", sourceEntity, destinationType, e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Mapping exception occurred: " + e.getMessage());
        }
    }
}
