package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.UserAssembler;
import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.controller.model.UserModel;
import com.epam.spring.cargo_delivery.service.UserService;
import com.epam.spring.cargo_delivery.service.api.UserApi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserApi {

  private final UserService userService;
  private final UserAssembler userAssembler;

  @Override
  public List<UserDTO> getAllUsers() {
    return userService.listUsers();
  }

  @Override
  public UserModel getUser(long id) {
    UserDTO userDTO = userService.getUser(id);
    return userAssembler.toModel(userDTO);
  }


  @Override
  public UserModel createUser(UserDTO userDto) {
    UserDTO outUserDTO = userService.createUser(userDto);
    return userAssembler.toModel(outUserDTO);
  }

  @Override
  public UserModel updateUser(long id,
      @RequestBody @Validated(OnUpdate.class) UserDTO userDto) {
    log.info("Update user by id {}", id);
    log.trace("Request body userDto {}", userDto);
    UserDTO outUserDTO = userService.updateUser(id, userDto);
    return userAssembler.toModel(outUserDTO);
  }

  @Override
  public ResponseEntity<Void> deleteUser(long id) {
    log.info("Delete user by id {}", id);
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

}