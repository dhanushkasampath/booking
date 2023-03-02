package com.alpha.hotel.hotelbookingbackend.repo;

import com.alpha.hotel.hotelbookingbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
}
