package com.epam.spring.cargo_delivery.service.mapper;

import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.service.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  List<UserDTO> mapUserDtos(List<User> users);

  UserDTO mapUserDto(User user);

  User mapUser(UserDTO userDTO);
}
