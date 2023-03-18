package com.alpha.hotel.hotelbookingbackend.repository;

import com.alpha.hotel.hotelbookingbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
}