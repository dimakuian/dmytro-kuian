package com.epam.spring.homework3.service.repository.impl;

import com.epam.spring.homework3.service.model.Order;
import com.epam.spring.homework3.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final List<Order> list = new ArrayList<>();

    @Override
    public Order getOrder(long id) {
        log.info("Get order for id {}", id);
        return list.stream().filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order is not found"));
    }

    @Override
    public List<Order> getOrders() {
        log.info("Get all orders");
        return new ArrayList<>(list);
    }

    @Override
    public Order createOrder(Order order) {
        log.info("Create order {}", order);
        list.add(order);
        return order;
    }

    @Override
    public Order updateOrder(long id, Order order) {
        log.info("Update order {}", order);
        boolean isDeleted = list.removeIf(o -> o.getId().equals(id));
        if (isDeleted) {
            list.add(order);
        } else {
            throw new RuntimeException("Order is not found");
        }
        return order;
    }

    @Override
    public void deleteOrder(long id) {
        list.removeIf(o -> o.getId().equals(id));
    }
}
