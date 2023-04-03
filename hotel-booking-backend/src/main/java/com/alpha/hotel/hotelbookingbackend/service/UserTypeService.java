package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.dto.ToggleUserTypeRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserTypeDto;
import com.alpha.hotel.hotelbookingbackend.entity.UserType;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;

import java.util.List;

public interface UserTypeService extends GenericService{

    UserType getUserTypeByType(UserTypeEnum userTypeEnum) throws HotelBookingException;

    List<UserTypeDto> getAllUserTypes() throws HotelBookingException;

    void toggleUser(ToggleUserTypeRequestDto toggleUserTypeRequestDto) throws HotelBookingException;
}
