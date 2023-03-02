package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String contactNo;
    private Date dateOfBirth;
    private String province;
    private String district;
    private String town;
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Message> messageList;



}
