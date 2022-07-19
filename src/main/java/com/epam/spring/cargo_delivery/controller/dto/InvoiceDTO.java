package com.epam.spring.cargo_delivery.controller.dto;

import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.epam.spring.cargo_delivery.service.model.InvoiceStatus;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {

  @Null(groups = OnCreate.class, message = "{common.id.empty}")
  @NotNull(groups = OnUpdate.class, message = "{common.id.not_empty}")
  private Long id;

  @NotNull(message = "{common.clientID.not_null}")
  private User user;

  @NotNull(message = "{invoice.creationDatetime.not_null}")
  private Timestamp creationDatetime;

  @NotNull(message = "{invoice.orderID.not_null}")
  private Order order;

  @Positive(message = "{invoice.sum.positive}")
  private Double sum;

  @NotNull(message = "{invoice.invoiceStatusID.not_null}")
  private InvoiceStatus invoiceStatus;
}
