package com.alpha.hotel.hotelbookingbackend.entity;


import com.alpha.hotel.hotelbookingbackend.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = Constants.DUPLICATE_USER_NAME,
                        columnNames = {"username"}),
                @UniqueConstraint(name = Constants.DUPLICATE_EMAIL,
                        columnNames = {"email"})
        })
@Where(clause = "is_deleted = 0")
public class User extends AbstractEntity {

    @Id
    private int userId;
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Message> messageList;
    @Column(nullable = false,
            columnDefinition = "BIT(1) NOT NULL DEFAULT b'1'")
    private boolean isActive = true;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userTypeId", nullable = false)
    private UserType userType;
}
