package com.epam.spring.homework3.service.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
public class Order {

    private long id;
    private long shippingAddressID;
    private long deliveryAddressID;
    private Timestamp creationTime;
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
    private Timestamp deliveryDate;

}
