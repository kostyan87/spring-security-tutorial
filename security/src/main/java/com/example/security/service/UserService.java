package com.example.security.service;

import com.example.security.dto.UserDto;
import com.example.security.entity.User;
import com.example.security.entity.VerificationToken;
import com.example.security.repository.UserRepository;
import com.example.security.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) {
        User user = User
                .builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role("USER")
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(user);
        return user;
    }

    public void saveVerificationTokenForUser(String token, User User) {
        VerificationToken verificationToken = new VerificationToken(User, token);
        verificationTokenRepository.save(verificationToken);
    }
}