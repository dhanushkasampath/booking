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
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    private String name;
    private String province;
    private String district;
    private String town;
  //  private String image;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Room> roomList;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<RestaurantTable> restaurantTableList;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Coupon> couponList;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Discount> discountList;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Review> reviewList;
}
