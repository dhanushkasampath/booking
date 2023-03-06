package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserLoginRequestDto implements Serializable {
    private static final long serialVersionUID = 5595304138483883021L;
    @NotNull(message = "User authentication is invalid. Please try again.")
    @NotEmpty(message = "User authentication is invalid. Please try again.")
    private String username;

    @NotNull(message = "User authentication is invalid. Please try again.")
    @NotEmpty(message = "User authentication is invalid. Please try again.")
    private String password;

}
