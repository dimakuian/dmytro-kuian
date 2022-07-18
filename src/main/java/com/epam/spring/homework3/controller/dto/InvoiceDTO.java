package com.epam.spring.homework3.controller.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InvoiceDTO {
    private long clientID;
    private Timestamp creationDatetime;
    private long orderID;
    private double sum;
    private int invoiceStatusID;
}
