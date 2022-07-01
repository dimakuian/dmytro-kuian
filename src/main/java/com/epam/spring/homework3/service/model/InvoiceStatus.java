package com.epam.spring.homework3.service.model;

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