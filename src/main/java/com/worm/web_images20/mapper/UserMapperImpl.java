package com.worm.web_images20.mapper;

import com.worm.web_images20.Dto.UserDTO;
import com.worm.web_images20.model.UserEntity;

public class UserMapperImpl implements UserMapper{
    @Override
    public UserEntity convertUserDtoToUser(UserDTO data) {
        UserEntity u = new UserEntity();
        u.setId(data.getId());
        u.setLogin(data.getLogin());
        u.setPassword(data.getPassword());
        return u;
    }
}
