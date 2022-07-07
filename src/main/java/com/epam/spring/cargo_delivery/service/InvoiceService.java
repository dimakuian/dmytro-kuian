package com.epam.spring.cargo_delivery.service;


import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import java.util.List;

public interface InvoiceService {

  InvoiceDTO getInvoice(long id);

  List<InvoiceDTO> getInvoices();

  InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

  InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

  void deleteInvoice(long id);
}
