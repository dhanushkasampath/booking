package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -7194256267271009624L;
    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be empty")
    private String userName;
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be empty")
    private String lastName;
    //    private String password;
    private String contactNo;
    private String dateOfBirth;
    private String province;
    private String district;
    private String town;
}


