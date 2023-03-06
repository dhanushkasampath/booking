package com.alpha.hotel.hotelbookingbackend.entity;

import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_type")
public class UserType {
    @Id
    private Long id;
    @Enumerated( EnumType.STRING )
    private UserTypeEnum userType;
    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<User> userList;
}
