package com.epam.spring.cargo_delivery.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "language")
public class Language {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String shortName;

  private String fullName;
}