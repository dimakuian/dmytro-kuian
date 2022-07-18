package com.epam.spring.homework3.service.mapper;

import com.epam.spring.homework3.controller.dto.OrderDTO;
import com.epam.spring.homework3.service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<OrderDTO> mapOrderDtos(List<Order> orders);

    OrderDTO mapOrderDto(Order order);

    Order mapOrder(OrderDTO orderDTO);
}
