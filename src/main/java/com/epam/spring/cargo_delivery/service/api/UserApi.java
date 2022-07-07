package com.epam.spring.cargo_delivery.service.api;

import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.controller.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "User management API")
@RequestMapping("api/v1/user")
public interface UserApi {

  @ApiOperation("Get all users")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  List<UserDTO> getAllUsers();

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "User id")
  })
  @ApiOperation("Get user")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  UserModel getUser(@PathVariable long id);

  @ApiOperation("Create user")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDTO userDto);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "User id")
  })
  @ApiOperation("Update user")
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{id}")
  UserModel updateUser(@PathVariable long id,
      @RequestBody @Validated(OnUpdate.class) UserDTO userDto);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "User id")
  })
  @ApiOperation("Delete user")
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteUser(@PathVariable long id);
}