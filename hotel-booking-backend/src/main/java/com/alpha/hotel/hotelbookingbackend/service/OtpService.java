package com.alpha.hotel.hotelbookingbackend.service;

import com.alpha.hotel.hotelbookingbackend.entity.Otp;

public interface OtpService {

    Otp findByContactNo(String contactNo);

    Otp create(String otpCode, String userContactNo);

    void update(Otp otp, String otpCode);
}
