package com.worm.web_images20.service;

import com.worm.web_images20.model.UserEntity;
import com.worm.web_images20.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void update(UserEntity user) {
        // no idea
    }

    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
