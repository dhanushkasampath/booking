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
@Table(name = "room_type")
public class RoomType {
    @Id
    private String typeId;
    private String typeName;
    private float size;
    private int capacity;

    @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Room> roomList;

}
