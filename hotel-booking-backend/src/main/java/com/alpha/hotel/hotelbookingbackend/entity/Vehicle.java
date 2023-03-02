package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private String vehicleNo;
    private String vehicle_type;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CabBookingInvoice> cabBookingInvoiceList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cabAdminId", nullable = false)
    private CabAdmin cabAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driverId")
    private Driver driver;

}
