package com.epam.spring.homework3.service;

import com.epam.spring.homework3.controller.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(long id);

    List<UserDTO> listUsers();

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(long id, UserDTO user);

    void deleteUser(long id);
}
