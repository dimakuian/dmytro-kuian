package com.epam.spring.cargo_delivery.service.repository;

import com.epam.spring.cargo_delivery.service.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<DeliveryOrder, Long> {

}
