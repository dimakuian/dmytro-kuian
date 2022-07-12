package com.epam.spring.cargo_delivery.service;


import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {

  InvoiceDTO getInvoice(long id);

  Page<InvoiceDTO> getInvoices(Pageable pageable);

  InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

  InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

  void deleteInvoice(long id);

  InvoiceDTO payInvoice(long id, InvoiceDTO invoiceDTO);
}
