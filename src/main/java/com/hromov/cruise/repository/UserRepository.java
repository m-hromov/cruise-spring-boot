package com.hromov.cruise.repository;

import com.hromov.cruise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u1 FROM User u1 WHERE u1.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
