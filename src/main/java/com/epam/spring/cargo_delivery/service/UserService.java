package com.epam.spring.cargo_delivery.service;

import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  UserDTO getUser(long id);

  Page<UserDTO> listUsers(Pageable pageable);

  UserDTO createUser(UserDTO userDTO);

  UserDTO updateUser(long id, UserDTO userDTO);

  void deleteUser(long id);

  boolean existByLogin(String login);
}
