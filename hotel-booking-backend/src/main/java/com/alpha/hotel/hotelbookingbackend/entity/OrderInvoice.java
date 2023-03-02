package com.alpha.hotel.hotelbookingbackend.entity;

;
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
@Table(name = "order_invoice")
public class OrderInvoice {
    @Id
    private String foodOrderId;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paymentId", nullable = false)
    private Payment payment;

    @OneToMany(mappedBy = "orderInvoice", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<RestaurantTable> restaurantTableList;

}
