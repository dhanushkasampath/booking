package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserOtpRequestDto implements Serializable {

    private static final long serialVersionUID = -8788841022421025307L;

    @NotNull(message = "username can not be null")
    @NotEmpty(message = "username can not be empty")
    private String userName;

    @NotNull(message = "otp can not be null")
    @NotEmpty(message = "otp can not be empty")
    private String otp;

}
