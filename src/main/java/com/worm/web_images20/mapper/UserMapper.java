package com.worm.web_images20.mapper;

import com.worm.web_images20.Dto.UserDTO;
import com.worm.web_images20.model.UserEntity;

public interface UserMapper {
    public UserEntity convertUserDtoToUser(UserDTO data);
}
