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
@Table(name = "discount")
public class Discount {
    @Id
    private String discountId;
    private Date validTill;
    private String discountName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotelId", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Payment> paymentList;
}
