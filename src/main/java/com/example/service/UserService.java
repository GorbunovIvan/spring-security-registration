package com.example.service;

import com.example.exception.UserAlreadyExistsException;
import com.example.model.Authority;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        if (user.getAuthorities().isEmpty()) {
            user.addAuthority(Authority.READ_BOOKS);
        }

        return userRepository.save(user);
    }
}
