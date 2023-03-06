package com.alpha.hotel.hotelbookingbackend.entity;


import com.alpha.hotel.hotelbookingbackend.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ToString(exclude = "userType")
@EqualsAndHashCode(callSuper = true, exclude = "userType")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reg_user",
        uniqueConstraints = {
                @UniqueConstraint(name = Constants.DUPLICATE_USER_NAME,
                        columnNames = {"username"}),
                @UniqueConstraint(name = Constants.DUPLICATE_EMAIL,
                        columnNames = {"email"})
        })
@Where(clause = "is_deleted = 0")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String contactNo;
    private Date dateOfBirth;
    private String province;
    private String district;
    private String town;
    private String email;
    private boolean passwordCreated;
    private boolean isActive;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Message> messageList;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;
}
