package com.epam.spring.cargo_delivery.service;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

  OrderDTO getOrder(long id);

  Page<OrderDTO> getOrders(Pageable pageable);

  OrderDTO createOrder(OrderDTO orderDTO);

  OrderDTO updateOrder(long id, OrderDTO orderDTO);

  void deleteOrder(long id);
}
