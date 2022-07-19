package com.epam.spring.cargo_delivery.service.impl;

import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.CREATION_TIME;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.ID;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.INVOICE_STATUS;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.INVOICE_STATUS_NAME;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.ORDER;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.PAID_INVOICE_STATUS;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.SUM;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.service.exception.EntityNotFoundException;
import com.epam.spring.cargo_delivery.service.mapper.InvoiceMapper;
import com.epam.spring.cargo_delivery.service.model.Invoice;
import com.epam.spring.cargo_delivery.service.repository.InvoiceRepository;
import com.epam.spring.cargo_delivery.service.repository.InvoiceStatusRepository;
import com.epam.spring.cargo_delivery.service.repository.UserRepository;
import com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

  @InjectMocks
  private InvoiceServiceImpl invoiceService;

  @Mock
  private InvoiceRepository invoiceRepository;

  @Mock
  private InvoiceStatusRepository invoiceStatusRepository;

  @Mock
  private UserRepository userRepository;

  @Test
  void getInvoiceTest() {
    Invoice testInvoice = TestInvoiceDataUtil.createInvoice();

    when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(testInvoice));

    InvoiceDTO returnInvoiceDTO = invoiceService.getInvoice(ID);

    assertThat(returnInvoiceDTO,
        allOf(
            hasProperty("id", equalTo(ID)),
            hasProperty("user", equalTo(USER)),
            hasProperty("creationDatetime", equalTo(CREATION_TIME)),
            hasProperty("order", equalTo(ORDER)),
            hasProperty("sum", equalTo(SUM)),
            hasProperty("invoiceStatus", equalTo(INVOICE_STATUS))));

    verify(invoiceRepository, times(1)).findById(anyLong());
  }

  @Test
  void getInvoiceWhenNotExistTest() {
    when(invoiceRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class, () -> invoiceService.getInvoice(ID));

    verify(invoiceRepository, times(1)).findById(anyLong());
  }

  @Test
  void getInvoicesTest() {
    InvoiceDTO testInvoiceDTO1 = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceDTO testInvoiceDTO2 = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceDTO testInvoiceDTO3 = TestInvoiceDataUtil.createInvoiceDTO();

    testInvoiceDTO2.setId(2L);
    testInvoiceDTO3.setId(3L);

    List<Invoice> invoices = Stream.of(testInvoiceDTO1, testInvoiceDTO2, testInvoiceDTO3)
        .map(InvoiceMapper.INSTANCE::mapInvoice)
        .collect(Collectors.toList());

    Pageable pageable = Pageable.unpaged();

    when(invoiceRepository.findAll(pageable))
        .thenReturn(new PageImpl<>(invoices, pageable, invoices.size()));

    Page<InvoiceDTO> returnInvoiceDTOS = invoiceService.getInvoices(pageable);

    assertThat(returnInvoiceDTOS, hasItems(testInvoiceDTO1, testInvoiceDTO2, testInvoiceDTO3));
    verify(invoiceRepository, times(1)).findAll(pageable);
  }

  @Test
  void createInvoiceTest() {
    Invoice testInvoice = TestInvoiceDataUtil.createInvoice();
    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();

    when(invoiceRepository.save(any())).thenReturn(testInvoice);
    when(invoiceStatusRepository.findByName(INVOICE_STATUS_NAME)).thenReturn(
        Optional.of(INVOICE_STATUS));

    InvoiceDTO returnInvoiceDTO = invoiceService.createInvoice(testInvoiceDTO);

    assertThat(returnInvoiceDTO,
        allOf(
            hasProperty("id", equalTo(testInvoice.getId())),
            hasProperty("user", equalTo(testInvoice.getUser())),
            hasProperty("creationDatetime", equalTo(testInvoice.getCreationDatetime())),
            hasProperty("order", equalTo(testInvoice.getOrder())),
            hasProperty("sum", equalTo(testInvoice.getSum())),
            hasProperty("invoiceStatus", equalTo(testInvoice.getInvoiceStatus()))));

    verify(invoiceRepository, times(1)).save(any());
    verify(invoiceStatusRepository, times(1)).findByName(INVOICE_STATUS_NAME);
  }

  @Test
  void updateInvoiceTest() {
    Invoice testInvoice = TestInvoiceDataUtil.createInvoice();
    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();

    when(invoiceRepository.existsById(anyLong())).thenReturn(true);
    when(invoiceRepository.save(any())).thenReturn(testInvoice);

    InvoiceDTO returnInvoiceDTO = invoiceService.updateInvoice(ID, testInvoiceDTO);

    assertThat(returnInvoiceDTO,
        allOf(
            hasProperty("id", equalTo(testInvoice.getId())),
            hasProperty("user", equalTo(testInvoice.getUser())),
            hasProperty("creationDatetime", equalTo(testInvoice.getCreationDatetime())),
            hasProperty("order", equalTo(testInvoice.getOrder())),
            hasProperty("sum", equalTo(testInvoice.getSum())),
            hasProperty("invoiceStatus", equalTo(testInvoice.getInvoiceStatus()))));

    verify(invoiceRepository, times(1)).existsById(anyLong());
    verify(invoiceRepository, times(1)).save(any());
  }

  @Test
  void updateWhenInvoiceNotExistTest() {
    when(invoiceRepository.existsById(anyLong())).thenReturn(false);

    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();

    assertThrows(EntityNotFoundException.class,
        () -> invoiceService.updateInvoice(ID, testInvoiceDTO));
    verify(invoiceRepository, times(1)).existsById(anyLong());
  }

  @Test
  void deleteInvoiceTest() {
    when(invoiceRepository.existsById(anyLong())).thenReturn(true);

    invoiceService.deleteInvoice(ID);

    verify(invoiceRepository, times(1)).existsById(anyLong());
    verify(invoiceRepository, times(1)).deleteById(anyLong());
  }

  @Test
  void deleteInvoiceWhenNotExistTest() {
    assertThrows(EntityNotFoundException.class, () -> invoiceService.deleteInvoice(ID));
    verify(invoiceRepository, times(1)).existsById(anyLong());
  }

  @Test
  void payInvoice() {
    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    Invoice testInvoice = TestInvoiceDataUtil.createInvoice();
    testInvoice.setInvoiceStatus(PAID_INVOICE_STATUS);
    testInvoiceDTO.getUser().setBalance(100.0);

    when(invoiceRepository.existsById(anyLong())).thenReturn(true);
    when(userRepository.save(any())).thenReturn(testInvoice.getUser());
    when(invoiceStatusRepository.findByName(anyString()))
        .thenReturn(Optional.of(PAID_INVOICE_STATUS));
    when(invoiceRepository.save(any())).thenReturn(testInvoice);

    InvoiceDTO returnInvoiceDTO = invoiceService.payInvoice(ID, testInvoiceDTO);

    assertThat(returnInvoiceDTO,
        allOf(
            hasProperty("id", equalTo(testInvoice.getId())),
            hasProperty("user", equalTo(testInvoice.getUser())),
            hasProperty("creationDatetime", equalTo(testInvoice.getCreationDatetime())),
            hasProperty("order", equalTo(testInvoice.getOrder())),
            hasProperty("sum", equalTo(testInvoice.getSum())),
            hasProperty("invoiceStatus", equalTo(testInvoice.getInvoiceStatus()))));

    verify(invoiceRepository, times(1)).existsById(anyLong());
    verify(userRepository, times(1)).save(any());
    verify(invoiceStatusRepository, times(1)).findByName(anyString());
    verify(invoiceRepository, times(1)).save(any());
  }

  @Test
  void payInvoiceWhenNotExist() {
    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();

    assertThrows(EntityNotFoundException.class,
        () -> invoiceService.payInvoice(ID, testInvoiceDTO));
    verify(invoiceRepository, times(1)).existsById(anyLong());
  }


  @Test
  void payInvoiceWhenUserDontHaveEnoughMoney() {
    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();

    when(invoiceRepository.existsById(anyLong())).thenReturn(true);

    assertThrows(RuntimeException.class, () -> invoiceService.payInvoice(ID, testInvoiceDTO),
        "User do not have enough money!");
    verify(invoiceRepository, times(1)).existsById(anyLong());
  }
}
