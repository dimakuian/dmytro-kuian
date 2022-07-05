package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Invoice {

    private Long id;
    private long clientID;
    private Timestamp creationDatetime;
    private long orderID;
    private double sum;
    private int invoiceStatusID;

}
