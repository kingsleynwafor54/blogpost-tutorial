package com.kingsCorner.kingsCorner.security.repository;

import com.kingsCorner.kingsCorner.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>  findByEmail(String email);
//    ApplicationConfig--- handling validations, password hashing etc
//    JwtAuthenticationFilter--- handling authentication and authorization,start with the filter first
//    JwtService--- controlling Jwt inbuilt methods
//    SecurityConfiguration-- the login and access to the application
}
