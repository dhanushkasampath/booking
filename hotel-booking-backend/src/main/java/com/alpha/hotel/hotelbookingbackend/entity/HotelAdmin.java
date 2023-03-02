package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "hotel_admin")
public class HotelAdmin {
    @Id
    private int hotelAdminId;
    private String firstName;
    private String lastName;
    private String password;
    private String contactNo;
    private Date dateOfBirth;
    private String province;
    private String district;
    private String town;
    private String email;
}
