package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsAccessTokenGenerateRequestDto implements Serializable {
    private static final long serialVersionUID = 8762078301428357740L;

    private String username;
    private String password;
}
