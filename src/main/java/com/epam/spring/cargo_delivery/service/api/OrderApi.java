package com.epam.spring.cargo_delivery.service.api;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.controller.model.OrderModel;
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

@Api(tags = "Order management API")
@RequestMapping("api/v1/order")
public interface OrderApi {

  @ApiOperation("Get all order")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  List<OrderDTO> getAllOrders();

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id")
  })
  @ApiOperation("Get order")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/{id}")
  OrderModel getOrder(@PathVariable long id);

  @ApiOperation("Create order")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  OrderModel createOrder(@RequestBody @Validated(OnCreate.class) OrderDTO orderDTO);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id")
  })
  @ApiOperation("Update order")
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{id}")
  OrderModel updateOrder(@PathVariable long id,
      @RequestBody @Validated(OnUpdate.class) OrderDTO orderDTO);

  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id")
  })
  @ApiOperation("Delete order")
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/{id}")
  ResponseEntity<Void> deleteOrder(@PathVariable long id);
}