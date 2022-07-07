package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.Invoice;
import com.epam.spring.cargo_delivery.service.model.enums.InvoiceStatus;
import java.util.List;

public interface InvoiceRepository {

  Invoice getInvoice(long id);

  List<Invoice> getInvoices();

  Invoice createInvoice(Invoice invoice);

  Invoice updateInvoice(long id, Invoice invoice);

  void deleteInvoice(long id);

  long countInvoiceByStatus(InvoiceStatus invoiceStatus);
}
