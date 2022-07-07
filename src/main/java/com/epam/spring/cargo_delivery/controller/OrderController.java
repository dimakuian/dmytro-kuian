package com.epam.spring.cargo_delivery.controller;

import com.epam.spring.cargo_delivery.controller.assembler.OrderAssembler;
import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.controller.model.OrderModel;
import com.epam.spring.cargo_delivery.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

  private final OrderService orderService;
  private final OrderAssembler orderAssembler;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<OrderDTO> getAllOrders() {
    return orderService.getOrders();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  public OrderModel getOrder(@PathVariable long id) {
    OrderDTO outOrderDto = orderService.getOrder(id);
    return orderAssembler.toModel(outOrderDto);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public OrderModel createOrder(@RequestBody @Validated(OnCreate.class) OrderDTO orderDTO) {
    OrderDTO outOrderDto = orderService.createOrder(orderDTO);
    return orderAssembler.toModel(outOrderDto);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{id}")
  public OrderModel updateOrder(@PathVariable long id,
      @RequestBody @Validated(OnUpdate.class) OrderDTO orderDTO) {
    log.info("Update order by id {}", id);
    log.trace("Request body orderDTO {}", orderDTO);
    OrderDTO outOrderDto = orderService.updateOrder(id, orderDTO);
    return orderAssembler.toModel(outOrderDto);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
    log.info("Delete order by id {}", id);
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
