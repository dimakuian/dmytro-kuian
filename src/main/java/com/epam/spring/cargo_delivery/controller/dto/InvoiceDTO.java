package com.epam.spring.cargo_delivery.controller.dto;

import com.epam.spring.cargo_delivery.controller.dto.group.OnCreate;
import com.epam.spring.cargo_delivery.controller.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {

  @Null(groups = OnCreate.class, message = "Id should be empty!")
  @NotNull(groups = OnUpdate.class, message = "Id should be not empty!")
  private Long id;

  @NotNull(message = "Client id should be not empty!")
  private Long clientID;

  @NotNull(message = "Creation date and time id should be not empty!")
  private Timestamp creationDatetime;

  @NotNull(message = "Order id should be not empty!")
  private Long orderID;

  @Positive(message = "Sum should be positive")
  private Double sum;

  @NotNull(message = "Invoice status id should be not empty!")
  private Integer invoiceStatusID;
}
