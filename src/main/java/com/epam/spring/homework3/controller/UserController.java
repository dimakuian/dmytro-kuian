package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.controller.assembler.UserAssembler;
import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.controller.dto.group.OnCreate;
import com.epam.spring.homework3.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.controller.model.UserModel;
import com.epam.spring.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.listUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public UserModel getUser(@PathVariable long id) {
        UserDTO userDTO = userService.getUser(id);
        return userAssembler.toModel(userDTO);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDTO userDto) {
        UserDTO outUserDTO = userService.createUser(userDto);
        return userAssembler.toModel(outUserDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public UserModel updateUser(@PathVariable long id, @RequestBody @Validated(OnUpdate.class) UserDTO userDto) {
        log.info("Update user by id {}", id);
        log.trace("Request body userDto {}", userDto);
        UserDTO outUserDTO = userService.updateUser(id, userDto);
        return userAssembler.toModel(outUserDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        log.info("Delete user by id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
