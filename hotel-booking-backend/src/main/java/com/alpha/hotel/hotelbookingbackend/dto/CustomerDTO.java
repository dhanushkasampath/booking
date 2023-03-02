package com.alpha.hotel.hotelbookingbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String contactNo;
    private String dateOfBirth;
    private String province;
    private String district;
    private String town;
    private String email;
}


