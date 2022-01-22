package com.epam.commonuserservice.service;

import com.epam.commonuserservice.entity.User;
import com.epam.commonuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void insertNewUser(User user) {
        userRepository.save(user);
    }

    public List<User> getClients() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserType().contentEquals("CLIENT"))
                .map(u -> new User(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getUserType()))
                .collect(Collectors.toList());
    }

}
