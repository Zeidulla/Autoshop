package com.online.auto.parts.service;

import com.online.auto.parts.entity.User;
import com.online.auto.parts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
