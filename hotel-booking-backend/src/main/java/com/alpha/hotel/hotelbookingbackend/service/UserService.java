package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.dto.UserDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginResponseDto;
import com.alpha.hotel.hotelbookingbackend.entity.User;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.util.UserLoginTypeEnum;

import java.io.UnsupportedEncodingException;

public interface UserService extends GenericService {
    User create(UserDto userDTO) throws HotelBookingException, UnsupportedEncodingException;

    UserLoginResponseDto userGeneralLogin(UserLoginRequestDto userLoginRequestDto) throws HotelBookingException;

    void userSpecialLogin(UserLoginRequestDto userLoginRequestDto, UserLoginTypeEnum forgetPasswordLogin) throws HotelBookingException;

    void forgetPassword(String email) throws HotelBookingException, UnsupportedEncodingException;

    User findOne(Long userId) throws HotelBookingException;
}
