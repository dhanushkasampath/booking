package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsSendRequestDto implements Serializable {
    private static final long serialVersionUID = 2956678092545957341L;
    private String campaignName;
    private String mask;
    private String numbers;
    private String content;
}
