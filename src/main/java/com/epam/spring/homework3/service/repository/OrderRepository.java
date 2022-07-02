package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.service.model.Order;

import java.util.List;

public interface OrderRepository {

    Order getOrder(long id);

    List<Order> getOrders();

    Order createOrder(Order order);

    Order updateOrder(long id, Order order);

    void deleteOrder(long id);
}
