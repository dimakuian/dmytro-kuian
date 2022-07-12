package com.epam.spring.cargo_delivery.controller.dto;


import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.service.model.Locality;
import com.epam.spring.cargo_delivery.service.model.ShippingStatus;
import com.epam.spring.cargo_delivery.service.model.User;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDTO {

  @Null(groups = OnCreate.class, message = "{common.id.empty}")
  @NotNull(groups = OnUpdate.class, message = "{common.id.not_empty}")
  private Long id;

  @NotNull(message = "{order.shippingAddressID.not_null}")
  private Locality shippingLocality;

  @NotNull(message = "{order.deliveryAddressID.not_null}")
  private Locality deliveryLocality;

  @NotNull(message = "{order.creationTime.not_null}")
  private Timestamp creationTime;

  @NotNull(message = "{common.clientID.not_null}")
  private User user;

  @NotBlank(message = "{order.consignee.not_null}")
  @Size(min = 2, message = "{order.consignee.size}")
  private String consignee;

  @NotBlank(message = "{order.description.not_null}")
  @Size(min = 2, message = "{order.description.size}")
  private String description;

  @NotNull(message = "{order.distance.not_null}")
  @Positive(message = "{order.distance.positive}")
  private Double distance;

  @NotNull(message = "{order.length.not_null}")
  @Positive(message = "{order.length.positive}")
  private Double length;

  @NotNull(message = "{order.height.not_null}")
  @Positive(message = "{order.height.positive}")
  private Double height;

  @NotNull(message = "{order.width.not_null}")
  @Positive(message = "{order.width.positive}")
  private Double width;

  @NotNull(message = "{order.weight.not_null}")
  @Positive(message = "{order.weight.positive}")
  private Double weight;

  @NotNull(message = "{order.volume.not_null}")
  @Positive(message = "{order.volume.positive}")
  private Double volume;

  @NotNull(message = "{order.fare.not_null}")
  @Positive(message = "{order.fare.positive}")
  private Double fare;

  @NotNull(message = "{order.statusID.not_null}")
  private ShippingStatus shippingStatus;

  @Null(groups = OnCreate.class, message = "{order.deliveryDate.null}")
  @NotNull(groups = OnUpdate.class, message = "{order.deliveryDate.not_null}")
  private Timestamp deliveryDate;
}
