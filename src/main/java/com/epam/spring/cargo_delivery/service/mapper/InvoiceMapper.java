package com.epam.spring.cargo_delivery.service.mapper;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvoiceMapper {

  InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

  InvoiceDTO mapInvoiceDto(Invoice invoice);

  Invoice mapInvoice(InvoiceDTO invoiceDTO);
}
