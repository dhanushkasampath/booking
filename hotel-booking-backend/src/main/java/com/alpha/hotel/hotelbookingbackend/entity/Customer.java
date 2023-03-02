package com.alpha.hotel.hotelbookingbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private int customerId;
    private String firstName;
    private String lastName;
    private String password;
    private String contactNo;
    private LocalDateTime dateOfBirth;
    private String province;
    private String district;
    private String town;
    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<BookingInvoice> bookingInvoiceList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<FoodItem> foodItemList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CabBookingInvoice> cabBookingInvoiceList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Refferal> refferalList;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Review> reviewList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private CustomerGrade customerGrade;
}
