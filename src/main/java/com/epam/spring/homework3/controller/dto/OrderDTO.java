package com.epam.spring.homework3.controller.dto;


import com.epam.spring.homework3.controller.dto.group.OnCreate;
import com.epam.spring.homework3.controller.dto.group.OnUpdate;
import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDTO {

  @Null(groups = OnCreate.class, message = "'id' should be empty!")
  @NotNull(groups = OnUpdate.class, message = "'id' should be not empty!")
  private Long id;

  @NotNull(message = "'shippingAddressID' should be not empty!")
  private Long shippingAddressID;

  @NotNull(message = "'deliveryAddressID' should be not empty!")
  private Long deliveryAddressID;

  @NotNull(message = "'creationTime' should be not empty!")
  private Timestamp creationTime;

  @NotNull(message = "'clientID' should be not empty!")
  private Long clientID;

  @NotBlank(message = "'consignee' should be not empty!")
  @Size(min = 2)
  private String consignee;

  @NotBlank(message = "'description' should be not empty!")
  @Size(min = 2)
  private String description;

  @NotNull(message = "'distance' should be not empty!")
  @Positive(message = "'distance' should be positive!")
  private Double distance;

  @NotNull(message = "'length' should be not empty!")
  @Positive(message = "'length' should be positive!")
  private Double length;

  @NotNull(message = "'height' should be not empty!")
  @Positive(message = "'height' should be positive!")
  private Double height;

  @NotNull(message = "'width' should be not empty!")
  @Positive(message = "'width' should be positive!")
  private Double width;

  @NotNull(message = "'weight' should be not empty!")
  @Positive(message = "'weight' should be positive!")
  private Double weight;

  @NotNull(message = "'volume' should be not empty!")
  @Positive(message = "'volume' should be positive!")
  private Double volume;

  @NotNull(message = "'fare' should be not empty!")
  @Positive(message = "'fare' should be positive!")
  private Double fare;

  @NotNull(message = "'statusID' should be not empty!")
  private Long statusID;

  @Null(groups = OnCreate.class, message = "'statusID' should be not empty!")
  @NotNull(groups = OnUpdate.class, message = "'statusID' should be not empty!")
  private Timestamp deliveryDate;
}
