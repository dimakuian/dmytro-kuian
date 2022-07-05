package com.epam.spring.homework3.service.model.enums;

import com.epam.spring.homework3.service.model.Invoice;

public enum InvoiceStatus {

  CREATED, PAID, DECLINED;

  public static InvoiceStatus getInvoiceStatus(Invoice invoice) {
    int statusID = invoice.getInvoiceStatusID();
    return InvoiceStatus.values()[statusID];

  }

  public String getName() {
    return name().toLowerCase();
  }

}