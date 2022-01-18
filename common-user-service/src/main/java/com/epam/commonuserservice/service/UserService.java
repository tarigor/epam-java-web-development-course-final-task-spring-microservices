package com.epam.commonuserservice.service;

import com.epam.commonuserservice.entity.User;
import com.epam.commonuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }

}
