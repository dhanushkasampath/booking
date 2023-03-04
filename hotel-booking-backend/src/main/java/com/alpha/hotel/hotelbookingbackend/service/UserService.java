package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.dto.UserDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginRequestDto;
import com.alpha.hotel.hotelbookingbackend.dto.UserLoginResponseDto;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.util.UserLoginTypeEnum;

public interface UserService {
    String saveCustomer(UserDto userDTO);

    UserLoginResponseDto userGeneralLogin(UserLoginRequestDto userLoginRequestDto) throws HotelBookingException;

    void userSpecialLogin(UserLoginRequestDto userLoginRequestDto, UserLoginTypeEnum forgetPasswordLogin) throws HotelBookingException;
}
