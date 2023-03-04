package com.alpha.hotel.hotelbookingbackend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @NotNull( message = "username cannot be null" )
    @NotEmpty( message = "username cannot be empty" )
    private String userName;

    @NotNull( message = "firstName cannot be null" )
    @NotEmpty( message = "firstName cannot be empty" )
    private String firstName;

    @NotNull( message = "lastName cannot be null" )
    @NotEmpty( message = "lastName cannot be empty" )
    private String lastName;
    private String password;
    private String contactNo;
    private String dateOfBirth;
    private String province;
    private String district;
    private String town;

    @NotNull( message = "email cannot be null" )
    @NotEmpty( message = "email cannot be empty" )
    private String email;
}


