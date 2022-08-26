package com.epam.spring.homework3.service.repository.impl;

import com.epam.spring.homework3.service.model.Invoice;
import com.epam.spring.homework3.service.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final List<Invoice> list = new ArrayList<>();

    @Override
    public Invoice getInvoice(long id) {
        log.info("Get invoice for id {}", id);
        return list.stream().filter(invoice -> invoice.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invoice is not found!"));
    }

    @Override
    public List<Invoice> getInvoices() {
        log.info("Get all invoices");
        return new ArrayList<>(list);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        log.info("Create invoice {}", invoice);
        list.add(invoice);
        return invoice;
    }

    @Override
    public Invoice updateInvoice(long id, Invoice invoice) {
        log.info("Update invoice with id {}", id);
        boolean isDeleted = list.removeIf(i -> i.getId().equals(id));
        if (isDeleted) {
            list.add(invoice);
        } else {
            throw new RuntimeException("Invoice is not found!");
        }
        return invoice;
    }

    @Override
    public void deleteInvoice(long id) {
        log.info("Delete invoice with id {}", id);
        list.removeIf(i -> i.getId().equals(id));
    }
}
