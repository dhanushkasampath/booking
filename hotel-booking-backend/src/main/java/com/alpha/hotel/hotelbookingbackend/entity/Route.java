package com.alpha.hotel.hotelbookingbackend.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "route")
public class Route {

    @Id
    private int routeNo;
    private String startPoint;
    private String endPoint;
}
