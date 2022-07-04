package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.controller.dto.OrderDTO;
import com.epam.spring.homework3.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getOrders();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public OrderDTO getOrder(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    public OrderDTO updateOrder(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
        log.info("Update order by id {}", id);
        log.trace("Request body orderDTO {}", orderDTO);
        return orderService.updateOrder(id, orderDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        log.info("Delete order by id {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
