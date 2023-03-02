package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "payment")
public class Payment {
    @Id
    private String paymentId;
    private Date date;
    private Time time;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BookingInvoice> bookingInvoiceListList;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paymentTypeId", nullable = false)
    private PaymentType paymentType;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderInvoice> orderInvoiceList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discountId", nullable = false)
    private Discount discount;

}
