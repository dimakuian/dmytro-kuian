package com.epam.spring.cargo_delivery.service;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import java.util.List;

public interface OrderService {

  OrderDTO getOrder(long id);

  List<OrderDTO> getOrders();

  OrderDTO createOrder(OrderDTO orderDTO);

  OrderDTO updateOrder(long id, OrderDTO orderDTO);

  void deleteOrder(long id);
}
