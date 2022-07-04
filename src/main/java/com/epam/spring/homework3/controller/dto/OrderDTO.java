package com.epam.spring.homework3.controller.dto;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class OrderDTO {

    @NotBlank(message = "Id should not be empty!")
    @Min(value = 0)
    private long id;
    private long shippingAddressID;
    private long deliveryAddressID;
    private LocalDate creationTime;
    private long clientID;
    private String consignee;
    private String description;
    private double distance;
    private double length;
    private double height;
    private double width;
    private double weight;
    private double volume;
    private double fare;
    private long statusID;
    private LocalDate deliveryDate;

}
