package com.hromov.cruise.repository;

import com.hromov.cruise.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT User FROM User AS u1 WHERE u1.email = us")
    Optional<User> findByEmail(String email);
}
