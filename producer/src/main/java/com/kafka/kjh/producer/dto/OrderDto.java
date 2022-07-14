package com.kafka.kjh.producer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
public class OrderDto {
    private String goods;
    private Long qty;

}
