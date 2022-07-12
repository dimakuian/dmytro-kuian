package com.epam.spring.cargo_delivery.service.impl;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.dto.InvoiceStatusDTO;
import com.epam.spring.cargo_delivery.service.InvoiceService;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.InvoiceMapper;
import com.epam.spring.cargo_delivery.service.mapper.InvoiceMapperImpl;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import com.epam.spring.cargo_delivery.service.model.User;
import com.epam.spring.cargo_delivery.service.repository.InvoiceRepository;
import com.epam.spring.cargo_delivery.service.repository.InvoiceStatusRepository;
import com.epam.spring.cargo_delivery.service.repository.UserRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

  private final InvoiceRepository invoiceRepository;
  private final InvoiceStatusRepository invoiceStatusRepository;
  private final UserRepository userRepository;

  @Override
  public InvoiceDTO getInvoice(long id) {
    log.info("Start getting delivery invoice by id {}", id);
    Invoice invoice = invoiceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    log.info("Finish getting delivery invoice by id {}", id);
    return InvoiceMapper.INSTANCE.mapInvoiceDto(invoice);
  }

  @Override
  public Page<InvoiceDTO> getInvoices(Pageable pageable) {
    log.info("Getting all invoices");
    return invoiceRepository.findAll(pageable).map(InvoiceMapper.INSTANCE::mapInvoiceDto);
  }

  @Override
  public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
    log.info("Start creating invoice {}", invoiceDTO);
    invoiceDTO.setCreationDatetime(Timestamp.valueOf(LocalDateTime.now()));

    invoiceDTO.setInvoiceStatus(
        invoiceStatusRepository.findByName(InvoiceStatusDTO.CREATED.name().toLowerCase())
            .orElseThrow(EntityNotFoundException::new)
    );

    Invoice invoice = InvoiceMapper.INSTANCE.mapInvoice(invoiceDTO);
    invoice = invoiceRepository.save(invoice);
    log.info("Finish creating invoice {}", invoice);

    return InvoiceMapper.INSTANCE.mapInvoiceDto(invoice);
  }

  @Override
  public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {
    log.info("Start updating invoice for id {}, invoice {}", id, invoiceDTO);

    if (!invoiceRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }

    Invoice invoice = InvoiceMapper.INSTANCE.mapInvoice(invoiceDTO);
    invoice = invoiceRepository.save(invoice);
    log.info("Finish updating invoice for id {}, invoice {}", id, invoiceDTO);

    return InvoiceMapper.INSTANCE.mapInvoiceDto(invoice);
  }

  @Override
  public void deleteInvoice(long id) {
    log.info("Start deleting invoice for id {}", id);

    if (!invoiceRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }

    log.info("Finish deleting invoice for id {}", id);
    invoiceRepository.deleteById(id);
  }

  @Transactional
  @Override
  public InvoiceDTO payInvoice(long id, InvoiceDTO invoiceDTO) {
    log.info("Start paying invoice {}", invoiceDTO);

    if (!invoiceRepository.existsById(id)) {
      throw new EntityNotFoundException();
    }

    if (invoiceDTO.getUser().getBalance() < invoiceDTO.getSum()) {
      throw new RuntimeException("User do not have enough money!");
    }

    User user = invoiceDTO.getUser();
    double currentBalance = user.getBalance();
    user.setBalance(currentBalance - invoiceDTO.getSum());
    userRepository.save(user);

    Invoice invoice = InvoiceMapperImpl.INSTANCE.mapInvoice(invoiceDTO);
    invoice.setInvoiceStatus(
        invoiceStatusRepository.findByName(InvoiceStatusDTO.PAID.name().toLowerCase())
            .orElseThrow(EntityNotFoundException::new)
    );
    invoiceRepository.save(invoice);

    log.info("Finish paying invoice {}", invoice);
    return InvoiceMapperImpl.INSTANCE.mapInvoiceDto(invoice);
  }
}
