package com.epam.spring.cargo_delivery.service;

import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import java.util.List;

public interface UserService {

  UserDTO getUser(long id);

  List<UserDTO> listUsers();

  UserDTO createUser(UserDTO userDTO);

  UserDTO updateUser(long id, UserDTO userDTO);

  void deleteUser(long id);
}
