package com.epam.spring.homework3.service;

import com.epam.spring.homework3.controller.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(long id);

    List<UserDTO> listUsers();

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(long id, UserDTO userDTO);

    void deleteUser(long id);
}
