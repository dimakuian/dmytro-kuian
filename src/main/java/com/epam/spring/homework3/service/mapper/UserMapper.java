package com.epam.spring.homework3.service.mapper;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDTO> mapUserDtos(List<User> users);

    UserDTO mapUserDto(User user);

    User mapUser(UserDTO userDTO);
}
