package com.epam.spring.cargo_delivery.service.impl;

import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.BALANCE;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.EMAIL;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.ID;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.LOGIN;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.NAME;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.PASSWORD;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.PHONE;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.ROLE;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.ROLE_NAME;
import static com.epam.spring.cargo_delivery.service.test.TestUserDataUtil.SURNAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.UserMapper;
import com.epam.spring.cargo_delivery.service.model.User;
import com.epam.spring.cargo_delivery.service.repository.RoleRepository;
import com.epam.spring.cargo_delivery.service.repository.UserRepository;
import com.epam.spring.cargo_delivery.service.test.TestUserDataUtil;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  @Test
  void getUserTest() {
    User testUser = TestUserDataUtil.createUser();

    when(userRepository.findById(ID)).thenReturn(Optional.of(testUser));

    UserDTO outUserDTO = userService.getUser(ID);

    assertThat(outUserDTO,
        allOf(
            hasProperty("id", equalTo(ID)),
            hasProperty("login", equalTo(LOGIN)),
            hasProperty("password", equalTo(PASSWORD)),
            hasProperty("role", equalTo(ROLE)),
            hasProperty("name", equalTo(NAME)),
            hasProperty("surname", equalTo(SURNAME)),
            hasProperty("email", equalTo(EMAIL)),
            hasProperty("phone", equalTo(PHONE)),
            hasProperty("balance", equalTo(BALANCE))));

    verify(userRepository, times(1)).findById(ID);
  }

  @Test
  void getUserWhenUserNotExistTest() {
    when(userRepository.findById(ID)).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> userService.getUser(ID));

    verify(userRepository, times(1)).findById(ID);
  }

  @Test
  void listUsersTest() {
    UserDTO userDTO1 = TestUserDataUtil.createUserDTO();
    UserDTO userDTO2 = TestUserDataUtil.createUserDTO();
    UserDTO userDTO3 = TestUserDataUtil.createUserDTO();

    userDTO2.setId(2L);
    userDTO2.setEmail(2 + EMAIL);

    userDTO3.setId(3L);
    userDTO3.setEmail(3 + EMAIL);

    List<User> users = Stream.of(userDTO1, userDTO2, userDTO3)
        .map(UserMapper.INSTANCE::mapUser)
        .collect(Collectors.toList());

    Pageable pageable = Pageable.unpaged();

    when(userRepository.findAll(pageable))
        .thenReturn(new PageImpl<>(users, pageable, users.size()));

    Page<UserDTO> resultUserDTOS = userService.listUsers(pageable);

    assertThat(resultUserDTOS.getContent(), hasItems(userDTO1, userDTO2, userDTO3));
    verify(userRepository, times(1)).findAll(pageable);
  }

  @Test
  void createUserTest() {
    User testUser = TestUserDataUtil.createUser();
    UserDTO testUserDTO = TestUserDataUtil.createUserDTO();

    when(userRepository.save(any())).thenReturn(testUser);
    when(roleRepository.findByName(ROLE_NAME)).thenReturn(Optional.of(ROLE));

    UserDTO userDTO = userService.createUser(testUserDTO);

    assertThat(userDTO,
        allOf(
            hasProperty("id", equalTo(testUser.getId())),
            hasProperty("login", equalTo(testUser.getLogin())),
            hasProperty("password", equalTo(testUser.getPassword())),
            hasProperty("role", equalTo(testUser.getRole())),
            hasProperty("name", equalTo(testUser.getName())),
            hasProperty("surname", equalTo(testUser.getSurname())),
            hasProperty("email", equalTo(testUser.getEmail())),
            hasProperty("phone", equalTo(testUser.getPhone())),
            hasProperty("balance", equalTo(testUser.getBalance()))));

    verify(roleRepository, times(1)).findByName(ROLE_NAME);
    verify(userRepository, times(1)).existsByEmail(EMAIL);
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void createUserWhenUserAlreadyExistTest() {
    UserDTO testUserDTO = TestUserDataUtil.createUserDTO();
    when(userRepository.existsByEmail(anyString())).thenReturn(true);

    assertThrows(RuntimeException.class, () -> userService.createUser(testUserDTO));
    verify(userRepository, times(1)).existsByEmail(anyString());
  }

  @Test
  void updateUserTest() {
    User testUser = TestUserDataUtil.createUser();
    UserDTO testUserDTO = TestUserDataUtil.createUserDTO();

    when(userRepository.existsById(ID)).thenReturn(true);
    when(userRepository.save(any())).thenReturn(testUser);

    UserDTO returnUserDTO = userService.updateUser(ID, testUserDTO);

    assertThat(returnUserDTO,
        allOf(
            hasProperty("id", equalTo(testUser.getId())),
            hasProperty("login", equalTo(testUser.getLogin())),
            hasProperty("password", equalTo(testUser.getPassword())),
            hasProperty("role", equalTo(testUser.getRole())),
            hasProperty("name", equalTo(testUser.getName())),
            hasProperty("surname", equalTo(testUser.getSurname())),
            hasProperty("email", equalTo(testUser.getEmail())),
            hasProperty("phone", equalTo(testUser.getPhone())),
            hasProperty("balance", equalTo(testUser.getBalance()))));

    verify(userRepository, times(1)).existsById(ID);
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void updateWhenUserNotExistTest() {
    when(userRepository.existsById(ID)).thenReturn(false);

    UserDTO testUserDTO = TestUserDataUtil.createUserDTO();

    assertThrows(RuntimeException.class, () -> userService.updateUser(ID, testUserDTO));

    verify(userRepository, times(1)).existsById(ID);
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void deleteUserTest() {
    when(userRepository.existsById(ID)).thenReturn(true);
    userService.deleteUser(ID);
    verify(userRepository, times(1)).deleteById(ID);
  }

  @Test
  void deleteUserWhenUserNotExistTest() {
    assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(ID));
    verify(userRepository, times(1)).existsById(ID);
  }
}
