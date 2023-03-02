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
@Table(name = "room")
public class Room {

    @Id
    private String roomId;
    private int buildingNo;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BookingInvoice> bookingInvoiceList;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roomTypeId", nullable = false)
    private RoomType roomType;



}
