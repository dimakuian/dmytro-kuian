package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.ShippingStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingStatusRepository extends JpaRepository<ShippingStatus, Long> {

  Optional<ShippingStatus> findByName(String name);
}
