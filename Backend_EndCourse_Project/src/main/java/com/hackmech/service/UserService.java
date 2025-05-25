package com.hackmech.service;

import com.hackmech.dto.UserDTO;
import com.hackmech.entity.User;
import com.hackmech.exception.UserNotFoundException;
import com.hackmech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        return user.getPassword().equals(password); // Add hash check in real apps
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public UserDTO getUserDtoById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String departmentName = user.getDepartment() != null ? user.getDepartment().getName() : null;

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getMobileNo(),
                user.getRole(),
                departmentName,
                user.getCreatedAt()
        );
    }

}
