package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.entity.UserType;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;

public interface UserTypeService {

    UserType getUserTypeByType(UserTypeEnum userTypeEnum) throws HotelBookingException;
}
