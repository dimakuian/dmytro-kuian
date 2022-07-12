package com.epam.spring.cargo_delivery.service;

import com.epam.spring.cargo_delivery.controller.dto.DeliveryOrderDTO;
import java.util.List;

public interface OrderService {

  DeliveryOrderDTO getOrder(long id);

  List<DeliveryOrderDTO> getOrders();

  DeliveryOrderDTO createOrder(DeliveryOrderDTO deliveryOrderDTO);

  DeliveryOrderDTO updateOrder(long id, DeliveryOrderDTO deliveryOrderDTO);

  void deleteOrder(long id);
}
