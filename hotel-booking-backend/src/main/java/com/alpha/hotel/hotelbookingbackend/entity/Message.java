package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "message")
public class Message {

    @Id
    private String messageId;
    private String notification;
    private String messageType;
    private String fromWhom;
    private String toWhom;
    private boolean mark_read;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;


}
