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
@Table(name = "payment_type")
public class PaymentType {

    @Id
    private String paymentTypeId;
    private String name;

    @OneToMany(mappedBy = "paymentType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Payment> paymentListList;


}
