package com.epam.spring.cargo_delivery.service.model.enums;

import com.epam.spring.cargo_delivery.service.model.Invoice;

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