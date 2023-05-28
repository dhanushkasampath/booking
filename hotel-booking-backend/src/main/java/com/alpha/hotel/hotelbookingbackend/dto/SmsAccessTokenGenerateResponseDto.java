package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsAccessTokenGenerateResponseDto implements Serializable {
    private static final long serialVersionUID = -2156234548130054076L;
    private String accessToken;
    private String refreshToken;
}
