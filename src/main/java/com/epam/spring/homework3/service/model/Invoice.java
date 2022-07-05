package com.epam.spring.homework3.service.model;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

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
