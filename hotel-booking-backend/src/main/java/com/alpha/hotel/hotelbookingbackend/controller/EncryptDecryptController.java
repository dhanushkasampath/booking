package com.alpha.hotel.hotelbookingbackend.controller;

import com.alpha.hotel.hotelbookingbackend.dto.EncryptDecryptRequestDto;
import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.EncryptDecryptService;
import com.alpha.hotel.hotelbookingbackend.util.ConversionTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
public class EncryptDecryptController {
    private final Logger logger = LoggerFactory.getLogger(EncryptDecryptController.class);
    @Value("${user.password.encryption.key}")
    private String secretKey;
    @Autowired
    private EncryptDecryptService encryptDecryptService;

    @GetMapping(path = "/convert")
    public ResponseEntity<String> encryptDecrypt(
            @RequestParam
            ConversionTypeEnum conversionType,
            @RequestBody
            EncryptDecryptRequestDto encryptDecryptRequestDto) throws HotelBookingException {
        if (conversionType.equals(ConversionTypeEnum.ENCRYPT)) {
            return new ResponseEntity<>(encryptDecryptService.encrypt(encryptDecryptRequestDto.getStringToConvert(), secretKey), HttpStatus.OK);
        } else if (conversionType.equals(ConversionTypeEnum.DECRYPT)) {
            return new ResponseEntity<>(encryptDecryptService.decrypt(encryptDecryptRequestDto.getStringToConvert(), secretKey), HttpStatus.OK);
        } else {
            logger.error("Invalid Conversion Type:{}", conversionType);
            throw new HotelBookingException(HttpStatus.BAD_REQUEST, "Invalid Conversion Type");
        }
    }
}
