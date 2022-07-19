package com.epam.spring.cargo_delivery.controller;

import static com.epam.spring.cargo_delivery.service.model.enums.ErrorType.VALIDATION_ERROR_TYPE;
import static com.epam.spring.cargo_delivery.service.test.util.TestUserDataUtil.EMAIL;
import static com.epam.spring.cargo_delivery.service.test.util.TestUserDataUtil.ID;
import static com.epam.spring.cargo_delivery.service.test.util.TestUserDataUtil.LOGIN;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.spring.cargo_delivery.controller.assembler.UserAssembler;
import com.epam.spring.cargo_delivery.controller.dto.UserDTO;
import com.epam.spring.cargo_delivery.controller.model.UserModel;
import com.epam.spring.cargo_delivery.service.UserService;
import com.epam.spring.cargo_delivery.service.test.config.TestConfig;
import com.epam.spring.cargo_delivery.service.test.util.TestUserDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
class UserServiceImplTest {

  @MockBean
  private UserService userService;

  @MockBean
  private UserAssembler userAssembler;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getUserTest() throws Exception {
    UserDTO testUserDTO = TestUserDataUtil.createUserDTO();
    UserModel userModel = new UserModel(testUserDTO);

    when(userService.getUser(ID)).thenReturn(testUserDTO);
    when(userAssembler.toModel(testUserDTO)).thenReturn(userModel);

    mockMvc
        .perform(
            get("/api/v1/user/" + ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID));

    verify(userService, times(1)).getUser(ID);
    verify(userAssembler, times(1)).toModel(testUserDTO);
  }

  @Test
  void listUsersTest() throws Exception {
    UserDTO testUserDTO1 = TestUserDataUtil.createUserDTO();
    UserDTO testUserDTO2 = TestUserDataUtil.createUserDTO();
    UserDTO testUserDTO3 = TestUserDataUtil.createUserDTO();

    testUserDTO2.setId(2L);
    testUserDTO3.setId(3L);

    Pageable pageable = PageRequest.of(0, 3);
    Page<UserDTO> userDTOPage = new PageImpl<>(Arrays.asList(
        testUserDTO1, testUserDTO2, testUserDTO3), pageable, 3);

    when(userService.listUsers(pageable)).thenReturn(userDTOPage);

    mockMvc
        .perform(
            get("/api/v1/user/")
                .queryParam("page", String.valueOf(0))
                .queryParam("size", String.valueOf(3)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.pageable.pageNumber").value(0))
        .andExpect(jsonPath("$.pageable.pageSize").value(3))
        .andExpect(jsonPath("$.content[0].id").value(testUserDTO1.getId()))
        .andExpect(jsonPath("$.content[1].id").value(testUserDTO2.getId()))
        .andExpect(jsonPath("$.content[2].id").value(testUserDTO3.getId()));

    verify(userService, times(1)).listUsers(pageable);
  }

  @Test
  void createValidUserTest() throws Exception {
    UserDTO inputUserDTO = TestUserDataUtil.createUserDTO();
    inputUserDTO.setId(null);
    inputUserDTO.setRole(null);
    inputUserDTO.setBalance(null);

    UserDTO returnedUserDTO = TestUserDataUtil.createUserDTO();
    UserModel userModel = new UserModel(returnedUserDTO);

    when(userService.createUser(inputUserDTO)).thenReturn(returnedUserDTO);
    when(userAssembler.toModel(returnedUserDTO)).thenReturn(userModel);

    mockMvc
        .perform(
            post("/api/v1/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputUserDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.login").value(LOGIN))
        .andExpect(jsonPath("$.email").value(EMAIL));

    verify(userService, times(1)).createUser(inputUserDTO);
  }

  @Test
  void createInvalidUserTest() throws Exception {
    UserDTO inputUserDTO = TestUserDataUtil.createUserDTO();
    inputUserDTO.setId(null);
    inputUserDTO.setRole(null);
    inputUserDTO.setEmail("not valid email");
    inputUserDTO.setPhone("not valid phone");

    when(userService.createUser(inputUserDTO)).thenReturn(inputUserDTO);

    mockMvc
        .perform(
            post("/api/v1/user/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputUserDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[1].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[2].errorType").value(VALIDATION_ERROR_TYPE.name()));
  }

  @Test
  void updateValidUserTest() throws Exception {
    UserDTO inputUserDTO = TestUserDataUtil.createUserDTO();
    UserDTO returnedUserDTO = TestUserDataUtil.createUserDTO();
    UserModel userModel = new UserModel(returnedUserDTO);

    when(userService.updateUser(ID, inputUserDTO)).thenReturn(returnedUserDTO);
    when(userAssembler.toModel(returnedUserDTO)).thenReturn(userModel);

    mockMvc
        .perform(
            patch("/api/v1/user/" + ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputUserDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.login").value(LOGIN))
        .andExpect(jsonPath("$.email").value(EMAIL));

    verify(userService, times(1)).updateUser(ID, inputUserDTO);
  }

  @Test
  void updateInvalidUserTest() throws Exception {
    UserDTO inputUserDTO = TestUserDataUtil.createUserDTO();
    inputUserDTO.setEmail("not valid email");
    inputUserDTO.setPhone("not valid phone");
    inputUserDTO.setBalance(-50.0);

    when(userService.updateUser(ID, inputUserDTO)).thenReturn(inputUserDTO);

    mockMvc
        .perform(
            patch("/api/v1/user/" + ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputUserDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[1].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[2].errorType").value(VALIDATION_ERROR_TYPE.name()));
  }

  @Test
  void deleteUserTest() throws Exception {
    doNothing().when(userService).deleteUser(ID);

    mockMvc
        .perform(
            delete("/api/v1/user/" + ID))
        .andDo(print())
        .andExpect(status().isNoContent());
  }
}
