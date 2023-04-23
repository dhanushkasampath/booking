package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.entity.Otp;
import com.alpha.hotel.hotelbookingbackend.repository.OtpRepository;
import com.alpha.hotel.hotelbookingbackend.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {
    private final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);

    @Value( "${otp.expiry.time.minutes}" )
    private String otpExpiryTimeInMinutes;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Otp findByContactNo(String contactNo) {
        Otp otp = otpRepository.findByContactNo(contactNo);
        if (otp != null) {
            return otp ;
        } else {
            return null;
        }
    }

    @Override
    public Otp create(String otpCode, String userContactNo) {
        Otp otp = new Otp();
        otp.setOtpCode(otpCode);
        otp.setContactNo(userContactNo);
        LocalDateTime expiryTime = getExpiryTime();
        otp.setExpiryTime(expiryTime);
        Otp generatedOtp = otpRepository.save(otp);
        if(generatedOtp != null){
            logger.info("OTP persisted successfully");
        }else{
            logger.error("Failed to persist new OTP.");
        }
        return generatedOtp;
    }

    @Override
    public void update(Otp otp, String otpCode) {
        otp.setOtpCode(otpCode);
        LocalDateTime expiryTime = getExpiryTime();
        otp.setExpiryTime(expiryTime);
        Otp updatedOtp = otpRepository.save(otp);
        if(updatedOtp != null){
            logger.info("OTP updated with new code successfully");
        }else{
            logger.error("Failed to update OTP with id:{}", otp.getOtpId());
        }
    }

    private LocalDateTime getExpiryTime() {
        return LocalDateTime.now().plusMinutes(Long.parseLong(otpExpiryTimeInMinutes));
    }
}
