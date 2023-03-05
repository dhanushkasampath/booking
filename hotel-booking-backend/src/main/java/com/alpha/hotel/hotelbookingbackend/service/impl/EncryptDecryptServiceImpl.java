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

import static javax.crypto.Cipher.DECRYPT_MODE;

@Service
public class EncryptDecryptServiceImpl implements EncryptDecryptService {
    private final Logger logger = LoggerFactory.getLogger(EncryptDecryptServiceImpl.class);
    private SecretKeySpec secretKeySpec;

    @Override
    public String encrypt(String stringTobeEncrypted, String secretKey) throws HotelBookingException {
        try {
            setKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(stringTobeEncrypted.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            logger.error("Error occurred while encrypting:", e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while encrypting");
        }
    }

    @Override
    public String decrypt(String stringTobeDecrypted, String secretKey) throws HotelBookingException {
        try {
            setKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(stringTobeDecrypted)));
        } catch (Exception e) {
            logger.error("Error occurred while decrypting:", e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
    }

    public void setKey(String myKey) throws HotelBookingException {
        try {
            secretKeySpec = new SecretKeySpec(myKey.getBytes(StandardCharsets.UTF_8), "AES");
        } catch (Exception e) {
            logger.error("Error occurred while creating secret key:", e);
            throw new HotelBookingException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while creating secret key");
        }
    }
}
