package com.epam.spring.cargo_delivery.controller;

import static com.epam.spring.cargo_delivery.service.model.enums.ErrorType.VALIDATION_ERROR_TYPE;
import static com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil.CONSIGNEE;
import static com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil.DELIVERY_DATE;
import static com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil.DESCRIPTION;
import static com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil.ID;
import static com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil.SHIPPING_LOCALITY;
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

import com.epam.spring.cargo_delivery.controller.assembler.OrderAssembler;
import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.model.OrderModel;
import com.epam.spring.cargo_delivery.service.OrderService;
import com.epam.spring.cargo_delivery.service.test.config.TestConfig;
import com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil;
import com.epam.spring.cargo_delivery.service.test.util.TestUserDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@Import(TestConfig.class)
class OrderControllerTest {

  @MockBean
  private OrderService orderService;

  @MockBean
  private OrderAssembler orderAssembler;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getAllOrdersTest() throws Exception {
    OrderDTO testOrderDTO1 = TestOrderDataUtil.createOrderDTO();
    OrderDTO testOrderDTO2 = TestOrderDataUtil.createOrderDTO();
    OrderDTO testOrderDTO3 = TestOrderDataUtil.createOrderDTO();

    testOrderDTO2.setId(2L);
    testOrderDTO3.setId(3L);

    Pageable pageable = PageRequest.of(0, 3);
    PageImpl<OrderDTO> orderDTOPage = new PageImpl<>(
        Arrays.asList(testOrderDTO1, testOrderDTO2, testOrderDTO3), pageable, 3);

    when(orderService.getOrders(pageable)).thenReturn(orderDTOPage);

    mockMvc
        .perform(
            get("/api/v1/order/")
                .queryParam("page", String.valueOf(0))
                .queryParam("size", String.valueOf(3)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.pageable.pageNumber").value(0))
        .andExpect(jsonPath("$.pageable.pageSize").value(3))
        .andExpect(jsonPath("$.content[0].id").value(testOrderDTO1.getId()))
        .andExpect(jsonPath("$.content[1].id").value(testOrderDTO2.getId()))
        .andExpect(jsonPath("$.content[2].id").value(testOrderDTO3.getId()));

    verify(orderService, times(1)).getOrders(pageable);
  }

  @Test
  void getOrderTest() throws Exception {
    OrderDTO testOrderDTO = TestOrderDataUtil.createOrderDTO();
    OrderModel orderModel = new OrderModel(testOrderDTO);

    when(orderService.getOrder(ID)).thenReturn(testOrderDTO);
    when(orderAssembler.toModel(testOrderDTO)).thenReturn(orderModel);

    mockMvc
        .perform(
            get("/api/v1/order/" + ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID));

    verify(orderService, times(1)).getOrder(ID);
    verify(orderAssembler, times(1)).toModel(testOrderDTO);
  }

  @Test
  void createValidOrderTest() throws Exception {
    OrderDTO inputOrderDTO = TestOrderDataUtil.createOrderDTO();
    inputOrderDTO.setId(null);

    OrderDTO returnedOrderDTO = TestOrderDataUtil.createOrderDTO();
    OrderModel orderModel = new OrderModel(returnedOrderDTO);

    when(orderService.createOrder(inputOrderDTO)).thenReturn(returnedOrderDTO);
    when(orderAssembler.toModel(returnedOrderDTO)).thenReturn(orderModel);

    mockMvc
        .perform(
            post("/api/v1/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputOrderDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.shippingLocality").value(SHIPPING_LOCALITY))
        .andExpect(jsonPath("$.consignee").value(CONSIGNEE))
        .andExpect(jsonPath("$.description").value(DESCRIPTION));

    verify(orderService, times(1)).createOrder(inputOrderDTO);
    verify(orderAssembler, times(1)).toModel(returnedOrderDTO);
  }

  @Test
  void createInvalidOrderTest() throws Exception {
    OrderDTO inputOrderDTO = TestOrderDataUtil.createOrderDTO();
    inputOrderDTO.setConsignee(null);
    inputOrderDTO.setFare(-500.0);
    inputOrderDTO.setWeight(-0.7);

    when(orderService.createOrder(inputOrderDTO)).thenReturn(inputOrderDTO);

    mockMvc
        .perform(
            post("/api/v1/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputOrderDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[1].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[2].errorType").value(VALIDATION_ERROR_TYPE.name()));
  }

  @Test
  void updateValidOrderTest() throws Exception {
    OrderDTO inputOrderDTO = TestOrderDataUtil.createOrderDTO();
    inputOrderDTO.setDeliveryDate(DELIVERY_DATE);
    OrderDTO returnedOrderDTO = TestOrderDataUtil.createOrderDTO();
    OrderModel orderModel = new OrderModel(returnedOrderDTO);

    when(orderService.updateOrder(ID, inputOrderDTO)).thenReturn(returnedOrderDTO);
    when(orderAssembler.toModel(returnedOrderDTO)).thenReturn(orderModel);

    mockMvc
        .perform(
            patch("/api/v1/order/" + ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputOrderDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.shippingLocality").value(SHIPPING_LOCALITY))
        .andExpect(jsonPath("$.consignee").value(CONSIGNEE))
        .andExpect(jsonPath("$.description").value(DESCRIPTION));

    verify(orderService, times(1)).updateOrder(ID, inputOrderDTO);
  }

  @Test
  void updateInvalidOrderTest() throws Exception {
    OrderDTO inputOrderDTO = TestOrderDataUtil.createOrderDTO();
    inputOrderDTO.setConsignee(null);
    inputOrderDTO.setFare(-500.0);
    inputOrderDTO.setWeight(-0.7);

    when(orderService.updateOrder(ID, inputOrderDTO)).thenReturn(inputOrderDTO);

    mockMvc
        .perform(
            patch("/api/v1/order/" + ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputOrderDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[1].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[2].errorType").value(VALIDATION_ERROR_TYPE.name()));
  }

  @Test
  void deleteOrderTest() throws Exception {
    doNothing().when(orderService).deleteOrder(ID);

    mockMvc
        .perform(
            delete("/api/v1/order/" + TestUserDataUtil.ID))
        .andDo(print())
        .andExpect(status().isNoContent());
  }
}
