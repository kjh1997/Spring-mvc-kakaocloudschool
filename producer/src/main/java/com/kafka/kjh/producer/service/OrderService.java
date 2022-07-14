package com.kafka.kjh.producer.service;

import com.kafka.kjh.producer.domain.Product;
import com.kafka.kjh.producer.dto.OrderDto;
import com.kafka.kjh.producer.kafka.KafkaProducer;
import com.kafka.kjh.producer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducer kafkaProducer;
    public Product Ordering(OrderDto orderDto) {
        Product order = new Product();
        order.setQty(orderDto.getQty());
        order.setProduct(orderDto.getGoods());
        orderRepository.save(order);
        kafkaProducer.send("example-goods-topic", orderDto);
        return order;

    }


}
