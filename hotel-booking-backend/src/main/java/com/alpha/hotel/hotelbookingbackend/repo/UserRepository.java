package com.alpha.hotel.hotelbookingbackend.repo;

import com.alpha.hotel.hotelbookingbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE u.userName = :username")
    User findByUserName(@Param("username") String userNameProvided);
}
