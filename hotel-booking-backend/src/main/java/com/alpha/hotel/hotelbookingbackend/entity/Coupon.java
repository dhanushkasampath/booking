package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "coupon")
public class Coupon {

    @Id
    private String couponId;
    private String title;
    private float discount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;

}
