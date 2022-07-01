package com.epam.spring.homework3.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
public class Invoice {

    private long id;
    private long clientID;
    private Timestamp creationDatetime;
    private long orderID;
    private double sum;
    private int invoiceStatusID;

}
