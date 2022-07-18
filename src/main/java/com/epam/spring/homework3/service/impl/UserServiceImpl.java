package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.service.UserService;
import com.epam.spring.homework3.service.mapper.UserMapper;
import com.epam.spring.homework3.service.model.User;
import com.epam.spring.homework3.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUser(long id) {
        log.info("Get user by id {}", id);
        User user = userRepository.getUser(id);
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public List<UserDTO> listUsers() {
        log.info("Get all users");
        List<User> users = userRepository.listUsers();
        return UserMapper.INSTANCE.mapUserDtos(users);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Create user {}", userDTO);
        User user = UserMapper.INSTANCE.mapUser(userDTO);
        user = userRepository.createUser(user);
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public UserDTO updateUser(long id, UserDTO userDTO) {
        log.info("Update user for id {}", id);
        User user = UserMapper.INSTANCE.mapUser(userDTO);
        user = userRepository.updateUser(id, user);
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public void deleteUser(long id) {
        log.info("Delete user for id {}", id);
        userRepository.deleteUser(id);
    }
}
