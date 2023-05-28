package com.alpha.hotel.hotelbookingbackend.repository;

import com.alpha.hotel.hotelbookingbackend.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    Otp findByContactNo(String contactNo);

}
