package com.epam.spring.cargo_delivery.controller;

import static com.epam.spring.cargo_delivery.service.model.enums.ErrorType.VALIDATION_ERROR_TYPE;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.ID;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.PAID_INVOICE_STATUS;
import static com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil.SUM;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.spring.cargo_delivery.controller.assembler.InvoiceAssembler;
import com.epam.spring.cargo_delivery.controller.dto.InvoiceDTO;
import com.epam.spring.cargo_delivery.controller.model.InvoiceModel;
import com.epam.spring.cargo_delivery.service.InvoiceService;
import com.epam.spring.cargo_delivery.service.test.config.TestConfig;
import com.epam.spring.cargo_delivery.service.test.util.TestInvoiceDataUtil;
import com.epam.spring.cargo_delivery.service.test.util.TestOrderDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceController.class)
@Import(TestConfig.class)
class InvoiceControllerTest {

  @MockBean
  private InvoiceService invoiceService;

  @MockBean
  private InvoiceAssembler invoiceAssembler;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getAllInvoiceTest() throws Exception {
    InvoiceDTO invoiceDTO1 = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceDTO invoiceDTO2 = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceDTO invoiceDTO3 = TestInvoiceDataUtil.createInvoiceDTO();

    invoiceDTO2.setId(2L);
    invoiceDTO3.setId(3L);

    Pageable pageable = PageRequest.of(0, 3);
    PageImpl<InvoiceDTO> invoiceDTOPage = new PageImpl<>(
        Arrays.asList(invoiceDTO1, invoiceDTO2, invoiceDTO3), pageable, 3);

    when(invoiceService.getInvoices(pageable)).thenReturn(invoiceDTOPage);

    mockMvc
        .perform(
            get("/api/v1/invoice/")
                .queryParam("page", String.valueOf(0))
                .queryParam("size", String.valueOf(3)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.pageable.pageNumber").value(0))
        .andExpect(jsonPath("$.pageable.pageSize").value(3))
        .andExpect(jsonPath("$.content[0].id").value(invoiceDTO1.getId()))
        .andExpect(jsonPath("$.content[1].id").value(invoiceDTO2.getId()))
        .andExpect(jsonPath("$.content[2].id").value(invoiceDTO3.getId()));

    verify(invoiceService, times(1)).getInvoices(pageable);
  }

  @Test
  void getInvoiceTest() throws Exception {
    InvoiceDTO testInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceModel invoiceModel = new InvoiceModel(testInvoiceDTO);

    when(invoiceService.getInvoice(ID)).thenReturn(testInvoiceDTO);
    when(invoiceAssembler.toModel(testInvoiceDTO)).thenReturn(invoiceModel);

    mockMvc
        .perform(
            get("/api/v1/invoice/" + TestOrderDataUtil.ID))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(TestOrderDataUtil.ID));

    verify(invoiceService, times(1)).getInvoice(ID);
    verify(invoiceAssembler, times(1)).toModel(testInvoiceDTO);
  }

  @Test
  void createValidInvoiceTest() throws Exception {
    InvoiceDTO inputInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    inputInvoiceDTO.setId(null);

    InvoiceDTO returnedInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceModel invoiceModel = new InvoiceModel(returnedInvoiceDTO);

    when(invoiceService.createInvoice(inputInvoiceDTO)).thenReturn(returnedInvoiceDTO);
    when(invoiceAssembler.toModel(returnedInvoiceDTO)).thenReturn(invoiceModel);

    mockMvc
        .perform(
            post("/api/v1/invoice")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputInvoiceDTO)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.sum").value(SUM));

    verify(invoiceService, times(1)).createInvoice(inputInvoiceDTO);
    verify(invoiceAssembler, times(1)).toModel(returnedInvoiceDTO);
  }

  @Test
  void createInvalidInvoiceTest() throws Exception {
    InvoiceDTO inputInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    inputInvoiceDTO.setSum(-5.0);
    inputInvoiceDTO.setOrder(null);

    when(invoiceService.createInvoice(inputInvoiceDTO)).thenReturn(inputInvoiceDTO);

    mockMvc
        .perform(
            post("/api/v1/invoice")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputInvoiceDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[1].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[2].errorType").value(VALIDATION_ERROR_TYPE.name()));
  }

  @Test
  void updateValidInvoiceTest() throws Exception {
    InvoiceDTO inputInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceDTO returnedInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceModel invoiceModel = new InvoiceModel(returnedInvoiceDTO);

    when(invoiceService.updateInvoice(ID, inputInvoiceDTO)).thenReturn(returnedInvoiceDTO);
    when(invoiceAssembler.toModel(inputInvoiceDTO)).thenReturn(invoiceModel);

    mockMvc
        .perform(
            patch("/api/v1/invoice/" + ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputInvoiceDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.sum").value(SUM));

    verify(invoiceService, times(1)).updateInvoice(ID, inputInvoiceDTO);
  }

  @Test
  void updateInvalidInvoiceTest() throws Exception {
    InvoiceDTO inputInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    inputInvoiceDTO.setSum(-5.0);
    inputInvoiceDTO.setId(null);

    when(invoiceService.updateInvoice(ID, inputInvoiceDTO)).thenReturn(inputInvoiceDTO);

    mockMvc
        .perform(
            patch("/api/v1/invoice/" + ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputInvoiceDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].errorType").value(VALIDATION_ERROR_TYPE.name()))
        .andExpect(jsonPath("$[1].errorType").value(VALIDATION_ERROR_TYPE.name()));
  }

  @Test
  void deleteInvoiceTest() throws Exception {
    doNothing().when(invoiceService).deleteInvoice(TestOrderDataUtil.ID);

    mockMvc
        .perform(
            delete("/api/v1/invoice/" + ID))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @Test
  void payInvoiceTest() throws Exception {
    InvoiceDTO inputInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    InvoiceDTO returnedInvoiceDTO = TestInvoiceDataUtil.createInvoiceDTO();
    returnedInvoiceDTO.setInvoiceStatus(PAID_INVOICE_STATUS);
    InvoiceModel invoiceModel = new InvoiceModel(returnedInvoiceDTO);

    when(invoiceService.payInvoice(ID, inputInvoiceDTO)).thenReturn(returnedInvoiceDTO);
    when(invoiceAssembler.toModel(returnedInvoiceDTO)).thenReturn(invoiceModel);

    mockMvc
        .perform(
            patch("/api/v1/invoice/" + ID + "/pay")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(inputInvoiceDTO)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(ID))
        .andExpect(jsonPath("$.invoiceStatus").value(PAID_INVOICE_STATUS));
  }
}
