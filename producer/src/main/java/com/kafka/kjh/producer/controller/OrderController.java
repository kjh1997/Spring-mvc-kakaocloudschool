package com.kafka.kjh.producer.controller;

import com.kafka.kjh.producer.dto.OrderDto;
import com.kafka.kjh.producer.repository.OrderRepository;
import com.kafka.kjh.producer.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;


    @PostMapping("/order")
    public String postOrder(@RequestBody OrderDto orderDto) {
        return orderService.Ordering(orderDto).getProduct();

    }

}
