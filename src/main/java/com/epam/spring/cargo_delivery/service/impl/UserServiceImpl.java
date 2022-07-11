package com.epam.spring.cargo_delivery.service.impl;

import com.epam.spring.cargo_delivery.controller.dto.RoleDTO;
import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.service.UserService;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.UserMapper;
import com.epam.spring.cargo_delivery.service.model.User;
import com.epam.spring.cargo_delivery.service.repository.RoleRepository;
import com.epam.spring.cargo_delivery.service.repository.UserRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public UserDTO getUser(long id) {
    log.info("Get user by id {}", id);
    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    return UserMapper.INSTANCE.mapUserDto(user);
  }

  @Override
  public List<UserDTO> listUsers() {
    log.info("Getting all users");
    List<User> users = userRepository.findAll();
    return UserMapper.INSTANCE.mapUserDtos(users);
  }

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    log.info("Started creating user");

    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new RuntimeException("This email is already taken!");
    }

    userDTO.setRole(
        roleRepository.findByName(RoleDTO.CLIENT.name())
            .orElseThrow(EntityNotFoundException::new)
    );

    userDTO.setBalance(BigDecimal.ZERO.doubleValue());
    User user = UserMapper.INSTANCE.mapUser(userDTO);
    user = userRepository.save(user);
    log.info("Created user {}", user);
    return UserMapper.INSTANCE.mapUserDto(user);
  }

  @Override
  public UserDTO updateUser(long id, UserDTO userDTO) {
    log.info("Start updating user for id {}", id);
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("Can not find this user!");
    }
    User user = UserMapper.INSTANCE.mapUser(userDTO);
    user = userRepository.save(user);
    log.info("Updated user {}", user);
    return UserMapper.INSTANCE.mapUserDto(user);
  }

  @Override
  public void deleteUser(long id) {
    log.info("Start deleting user for id {}", id);
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("Can not find this user!");
    }
    log.info("Deleted user by id {}", id);
    userRepository.deleteById(id);
  }
}
