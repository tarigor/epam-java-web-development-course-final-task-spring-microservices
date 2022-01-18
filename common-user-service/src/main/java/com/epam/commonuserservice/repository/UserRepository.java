package com.epam.commonuserservice.repository;


import com.epam.commonuserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findUserByUserEmail(String email);
}
