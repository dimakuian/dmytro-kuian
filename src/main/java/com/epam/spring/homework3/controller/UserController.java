package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<UserDTO> getAllUsers() {
        return userService.listUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO userDto) {
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDto) {
        log.info("Update user by id {}", id);
        log.trace("Request body userDto {}", userDto);
        return userService.updateUser(id, userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        log.info("Delete user by id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
