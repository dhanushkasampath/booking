package com.alpha.hotel.hotelbookingbackend.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "food_order_invoice")
public class FoodOrderInvoice {

    @Id
    private String foodId;
    private String foodOrderId;
    private float quantity;


}
