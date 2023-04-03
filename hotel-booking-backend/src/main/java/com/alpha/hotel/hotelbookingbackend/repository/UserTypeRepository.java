package com.alpha.hotel.hotelbookingbackend.repository;

import com.alpha.hotel.hotelbookingbackend.entity.UserType;
import com.alpha.hotel.hotelbookingbackend.util.UserTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByUserType(UserTypeEnum userTypeEnum);
}
