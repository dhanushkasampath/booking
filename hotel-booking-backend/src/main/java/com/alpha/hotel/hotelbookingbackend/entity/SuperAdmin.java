package com.alpha.hotel.hotelbookingbackend.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "super_admin")
public class SuperAdmin {

    @Id
    private int superAdminId;
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
