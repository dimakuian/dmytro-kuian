package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.service.model.Invoice;

import java.util.List;

public interface InvoiceRepository {

    Invoice getInvoice(long id);

    List<Invoice> getInvoices();

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(long id, Invoice invoice);

    void deleteInvoice(long id);

}
