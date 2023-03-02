package com.alpha.hotel.hotelbookingbackend.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "driver")
public class Driver {
    @Id
    private String driverId;
    private String driverName;
    private String driverPhoneNo;
    private String driverEmail;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleNo")
    private Vehicle vehicle;

}
