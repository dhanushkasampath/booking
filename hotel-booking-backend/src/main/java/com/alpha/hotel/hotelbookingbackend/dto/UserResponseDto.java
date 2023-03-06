package com.alpha.hotel.hotelbookingbackend.dto;

import java.io.Serializable;

public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = -4588237030519047527L;
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String dateOfBirth;
    private String province;
    private String district;
    private String town;
}
