package com.workspace.booking.service;

import com.workspace.booking.dto.UserRequestDto;
import com.workspace.booking.entity.Office;
import com.workspace.booking.entity.User;
import com.workspace.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserRequestDto requestDto){

        if (userRepository.existsByEmail(requestDto.getEmail())){
            throw new IllegalStateException("Данная электроная почта уже используется");
        }

        User user = new User();
        user.setFullName(requestDto.getFullName());
        user.setEmail(requestDto.getEmail());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

}
