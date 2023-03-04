package com.alpha.hotel.hotelbookingbackend.service.impl;

import com.alpha.hotel.hotelbookingbackend.exception.HotelBookingException;
import com.alpha.hotel.hotelbookingbackend.service.EncryptDecryptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EncryptDecryptServiceImpl implements EncryptDecryptService {
    private final Logger logger = LoggerFactory.getLogger(EncryptDecryptServiceImpl.class);
    private SecretKeySpec secretKeySpec;
    public void setKey(String myKey) throws HotelBookingException {
        try {
            secretKeySpec = new SecretKeySpec(myKey.getBytes(StandardCharsets.UTF_8), "AES");
        }
        catch ( Exception e ) {
            logger.error("Error occurred while creating secret key:", e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while creating secret key");
        }
    }

    public String encrypt(String providedPassword, String secretKey) throws HotelBookingException {
        try {
            setKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(providedPassword.getBytes(StandardCharsets.UTF_8)));
        }
        catch ( Exception e ) {
            logger.error("Error occurred while encrypting:", e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while encrypting");
        }
    }
}
