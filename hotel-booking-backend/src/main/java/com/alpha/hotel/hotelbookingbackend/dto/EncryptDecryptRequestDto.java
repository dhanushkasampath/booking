package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EncryptDecryptRequestDto implements Serializable {
    private static final long serialVersionUID = 2163405351820830390L;
    private String stringToConvert;
}
