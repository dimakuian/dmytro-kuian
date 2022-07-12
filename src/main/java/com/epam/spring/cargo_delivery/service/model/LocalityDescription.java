package com.epam.spring.cargo_delivery.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locality_description")
public class LocalityDescription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String city;

  private String street;

  private String streetNumber;

  @ManyToOne
  @JoinColumn(name = "language_id", referencedColumnName = "id")
  private Language language;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "locality_id", nullable = false)
  @ToString.Exclude
  private Locality locality;
}
