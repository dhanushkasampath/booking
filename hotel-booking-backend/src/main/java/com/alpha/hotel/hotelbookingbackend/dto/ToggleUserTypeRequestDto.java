package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ToggleUserTypeRequestDto implements Serializable {
    private static final long serialVersionUID = -314799020354018990L;
    @NotNull(message = "userName can not be null")
    @NotEmpty(message = "userName can not be empty")
    private String userName;

    @NotNull(message = "new user type can not be null")
    @NotEmpty(message = "new user type can not be empty")
    private String newUserType;

}
