package com.epam.spring.homework3.service;


import com.epam.spring.homework3.controller.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO getInvoice(long id);

    List<InvoiceDTO> getInvoices();

    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

    void deleteInvoice(long id);
}
