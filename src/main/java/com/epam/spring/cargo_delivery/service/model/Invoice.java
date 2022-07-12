package com.epam.spring.cargo_delivery.service.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice")
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  private Timestamp creationDatetime;

  @OneToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private DeliveryOrder order;

  private double sum;

  @ManyToOne
  @JoinColumn(name = "invoice_status_id", referencedColumnName = "id")
  private InvoiceStatus invoiceStatus;
}
