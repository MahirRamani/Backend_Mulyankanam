package com.svm.Mulyankanam.service;

import com.svm.Mulyankanam.model.User;
import com.svm.Mulyankanam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
