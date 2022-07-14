package com.kafka.kjh.producer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "order_id", unique = true)
    private Long id;


    @Column(nullable = false)
    private Long qty;

    @Column(nullable = false)
    private String product;
    private LocalDateTime localDateTime;
    public Product(){
        localDateTime = LocalDateTime.now();
    }
}
