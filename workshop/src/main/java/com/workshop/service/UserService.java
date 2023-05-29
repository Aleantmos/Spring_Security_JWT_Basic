package com.workshop.service;

import com.workshop.model.dto.MyUserDetails;
import com.workshop.model.entity.User;
import com.workshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }

    public MyUserDetails findMyUserDetailsByEmail(String email) {
        User userByEmail = findUserByEmail(email);
        return mapUserToMyUserDetails(userByEmail);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public MyUserDetails mapUserToMyUserDetails(User user) {
        return modelMapper.map(user, MyUserDetails.class);
    }
}
