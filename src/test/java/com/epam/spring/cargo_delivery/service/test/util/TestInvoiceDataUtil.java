package com.epam.spring.cargo_delivery.service.test.util;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.dto.InvoiceStatusDTO;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import com.epam.spring.cargo_delivery.service.model.InvoiceStatus;
import com.epam.spring.cargo_delivery.service.model.Order;
import com.epam.spring.cargo_delivery.service.model.User;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestInvoiceDataUtil {

  public static final Long ID = 1L;
  public static final User USER = TestUserDataUtil.createUser();
  public static final Timestamp CREATION_TIME = Timestamp.valueOf("2022-07-19 10:24:23");
  public static final Order ORDER = TestOrderDataUtil.createOrder();
  public static final double SUM = 65.6;
  public static final InvoiceStatus INVOICE_STATUS = createInvoiceStatus();
  public static final InvoiceStatus PAID_INVOICE_STATUS = createPaidInvoiceStatus();
  public static final String INVOICE_STATUS_NAME = "created";

  public static Invoice createInvoice() {
    return Invoice.builder()
        .id(ID)
        .user(USER)
        .creationDatetime(CREATION_TIME)
        .order(ORDER)
        .sum(SUM)
        .invoiceStatus(INVOICE_STATUS)
        .build();
  }

  public static InvoiceDTO createInvoiceDTO() {
    return InvoiceDTO.builder()
        .id(ID)
        .user(USER)
        .creationDatetime(CREATION_TIME)
        .order(ORDER)
        .sum(SUM)
        .invoiceStatus(INVOICE_STATUS)
        .build();
  }

  private static InvoiceStatus createInvoiceStatus() {
    return InvoiceStatus.builder()
        .id(1L)
        .name(InvoiceStatusDTO.CREATED.name().toLowerCase())
        .build();
  }

  private static InvoiceStatus createPaidInvoiceStatus() {
    return InvoiceStatus.builder()
        .id(2L)
        .name(InvoiceStatusDTO.PAID.name().toLowerCase())
        .build();
  }
}
