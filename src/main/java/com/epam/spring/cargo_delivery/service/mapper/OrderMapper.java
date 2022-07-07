package com.epam.spring.cargo_delivery.service.mapper;

import com.epam.spring.cargo_delivery.controller.dto.OrderDTO;
import com.epam.spring.cargo_delivery.service.model.Order;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<OrderDTO> mapOrderDtos(List<Order> orders);

    OrderDTO mapOrderDto(Order order);

    Order mapOrder(OrderDTO orderDTO);
}
