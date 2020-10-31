package com.worm.web_images20.service;

import com.worm.web_images20.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(UserEntity user);
    void update(UserEntity user);
}
