package com.epam.spring.cargo_delivery.service.mapper;

import com.epam.spring.cargo_delivery.controller.dto.DeliveryOrderDTO;
import com.epam.spring.cargo_delivery.service.model.DeliveryOrder;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryOrderMapper {

  DeliveryOrderMapper INSTANCE = Mappers.getMapper(DeliveryOrderMapper.class);

  List<DeliveryOrderDTO> mapOrderDtos(List<DeliveryOrder> orders);

  DeliveryOrderDTO mapOrderDto(DeliveryOrder deliveryOrder);

  DeliveryOrder mapOrder(DeliveryOrderDTO deliveryOrderDTO);
}
