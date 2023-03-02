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
@Table(name = "review")
public class Review {

    @Id
    private String reviewId;
    private String Description;
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

}
